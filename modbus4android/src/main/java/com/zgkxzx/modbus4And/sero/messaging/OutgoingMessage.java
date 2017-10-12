package com.zgkxzx.modbus4And.sero.messaging;

public interface OutgoingMessage {
    /**
     * Return the byte array representing the serialization of the request.
     * 
     * @return byte array representing the serialization of the request
     */
    byte[] getMessageData();
}
