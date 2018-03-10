package com.zgkxzx.modbus4And.requset;

import com.zgkxzx.modbus4And.base.ModbusUtils;

/**
 * 创建者   zgkxzx
 * 创建日期 2017/10/12.
 * 功能描述
 */

public class ModbusParam {
    public String host;
    public int port = ModbusUtils.TCP_PORT;
    public boolean encapsulated;

    public int timeout = 500;
    public int retries = 2;
    public boolean keepAlive = true;

    /**
     * If connection is established with slave/slaves
     */
    public boolean connected = false;


    public ModbusParam setHost(String host) {
        this.host = host;
        return this;
    }



    public ModbusParam setPort(int port) {
        this.port = port;
        return this;
    }



    public ModbusParam setEncapsulated(boolean encapsulated) {
        this.encapsulated = encapsulated;
        return this;
    }



    public ModbusParam setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }



    public ModbusParam setRetries(int retries) {
        this.retries = retries;
        return this;
    }



    public ModbusParam setConnected(boolean connected) {
        this.connected = connected;
        return this;
    }



    public ModbusParam setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
        return this;
    }
}
