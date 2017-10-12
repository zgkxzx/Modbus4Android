package com.zgkxzx.modbus4And.requset;

import com.zgkxzx.modbus4And.base.ModbusUtils;

/**
 * 创建者   zgkxzx
 * 创建日期 2017/10/12.
 * 功能描述
 */

public class ModbusParam {
    private String host;
    private int port = ModbusUtils.TCP_PORT;
    private boolean encapsulated;

    private int timeout = 500;
    private int retries = 2;
    private boolean keepAlive = true;

    /**
     * If connection is established with slave/slaves
     */
    protected boolean connected = false;


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isEncapsulated() {
        return encapsulated;
    }

    public void setEncapsulated(boolean encapsulated) {
        this.encapsulated = encapsulated;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getRetries() {
        return retries;
    }

    public void setRetries(int retries) {
        this.retries = retries;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public boolean isKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
    }
}
