/*
/*
 * ============================================================================
 * GNU General Public License
 * ============================================================================
 *
 * Copyright (C) 2006-2011 Serotonin Software Technologies Inc. http://serotoninsoftware.com
 * @author Matthew Lohbihler
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.zgkxzx.modbus4And.ip.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.zgkxzx.modbus4And.ModbusSlaveSet;
import com.zgkxzx.modbus4And.base.BaseMessageParser;
import com.zgkxzx.modbus4And.base.BaseRequestHandler;
import com.zgkxzx.modbus4And.base.ModbusUtils;
import com.zgkxzx.modbus4And.exception.ModbusInitException;
import com.zgkxzx.modbus4And.ip.encap.EncapMessageParser;
import com.zgkxzx.modbus4And.ip.encap.EncapRequestHandler;
import com.zgkxzx.modbus4And.ip.xa.XaMessageParser;
import com.zgkxzx.modbus4And.ip.xa.XaRequestHandler;
import com.zgkxzx.modbus4And.sero.messaging.MessageControl;
import com.zgkxzx.modbus4And.sero.messaging.TestableTransport;

public class TcpSlave extends ModbusSlaveSet {
    // Configuration fields
    private final int port;
    final boolean encapsulated;

    // Runtime fields.
    private ServerSocket serverSocket;
    final ExecutorService executorService;
    final List<TcpConnectionHandler> listConnections = new ArrayList<>();

    public TcpSlave(boolean encapsulated) {
        this(ModbusUtils.TCP_PORT, encapsulated);
    }

    public TcpSlave(int port, boolean encapsulated) {
        this.port = port;
        this.encapsulated = encapsulated;
        executorService = Executors.newCachedThreadPool();
    }

    @Override
    public void start() throws ModbusInitException {
        try {
            serverSocket = new ServerSocket(port);

            Socket socket;
            while (true) {
                socket = serverSocket.accept();
                TcpConnectionHandler handler = new TcpConnectionHandler(socket);
                executorService.execute(handler);
                synchronized (listConnections) {
                    listConnections.add(handler);
                }
            }
        }
        catch (IOException e) {
            throw new ModbusInitException(e);
        }
    }

    @Override
    public void stop() {
        // Close the socket first to prevent new messages.
        try {
            serverSocket.close();
        }
        catch (IOException e) {
            getExceptionHandler().receivedException(e);
        }

        // Close all open connections.
        synchronized (listConnections) {
            for (TcpConnectionHandler tch : listConnections)
                tch.kill();
            listConnections.clear();
        }

        // Now close the executor service.
        executorService.shutdown();
        try {
            executorService.awaitTermination(3, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            getExceptionHandler().receivedException(e);
        }
    }

    class TcpConnectionHandler implements Runnable {
        private final Socket socket;
        private TestableTransport transport;
        private MessageControl conn;

        TcpConnectionHandler(Socket socket) throws ModbusInitException {
            this.socket = socket;
            try {
                transport = new TestableTransport(socket.getInputStream(), socket.getOutputStream());
            }
            catch (IOException e) {
                throw new ModbusInitException(e);
            }
        }

        @Override
        public void run() {
            BaseMessageParser messageParser;
            BaseRequestHandler requestHandler;

            if (encapsulated) {
                messageParser = new EncapMessageParser(false);
                requestHandler = new EncapRequestHandler(TcpSlave.this);
            }
            else {
                messageParser = new XaMessageParser(false);
                requestHandler = new XaRequestHandler(TcpSlave.this);
            }

            conn = new MessageControl();
            conn.setExceptionHandler(getExceptionHandler());

            try {
                conn.start(transport, messageParser, requestHandler, null);
                executorService.execute(transport);
            }
            catch (IOException e) {
                getExceptionHandler().receivedException(new ModbusInitException(e));
            }

            // Monitor the socket to detect when it gets closed.
            while (true) {
                try {
                    transport.testInputStream();
                }
                catch (IOException e) {
                    break;
                }

                try {
                    Thread.sleep(500);
                }
                catch (InterruptedException e) {
                    // no op
                }
            }

            conn.close();
            kill();
            synchronized (listConnections) {
                listConnections.remove(this);
            }
        }

        void kill() {
            try {
                socket.close();
            }
            catch (IOException e) {
                getExceptionHandler().receivedException(new ModbusInitException(e));
            }
        }
    }
}
