package com.zgkxzx.modbus4And.serial;

import com.zgkxzx.modbus4And.msg.ModbusMessage;

abstract public class SerialMessage {
    protected final ModbusMessage modbusMessage;

    public SerialMessage(ModbusMessage modbusMessage) {
        this.modbusMessage = modbusMessage;
    }

    public ModbusMessage getModbusMessage() {
        return modbusMessage;
    }

    @Override
    public String toString() {
        return "SerialMessage [modbusMessage=" + modbusMessage + "]";
    }
}
