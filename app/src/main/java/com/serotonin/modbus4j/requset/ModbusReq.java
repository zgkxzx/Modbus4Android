package com.serotonin.modbus4j.requset;

import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.msg.ReadCoilsRequest;
import com.serotonin.modbus4j.msg.ReadCoilsResponse;
import com.serotonin.modbus4j.msg.ReadDiscreteInputsRequest;
import com.serotonin.modbus4j.msg.ReadDiscreteInputsResponse;
import com.serotonin.modbus4j.msg.ReadExceptionStatusRequest;
import com.serotonin.modbus4j.msg.ReadExceptionStatusResponse;
import com.serotonin.modbus4j.msg.ReadHoldingRegistersRequest;
import com.serotonin.modbus4j.msg.ReadHoldingRegistersResponse;
import com.serotonin.modbus4j.msg.ReadInputRegistersRequest;
import com.serotonin.modbus4j.msg.ReadInputRegistersResponse;
import com.serotonin.modbus4j.msg.ReportSlaveIdRequest;
import com.serotonin.modbus4j.msg.ReportSlaveIdResponse;
import com.serotonin.modbus4j.msg.WriteCoilRequest;
import com.serotonin.modbus4j.msg.WriteCoilResponse;
import com.serotonin.modbus4j.msg.WriteCoilsRequest;
import com.serotonin.modbus4j.msg.WriteCoilsResponse;
import com.serotonin.modbus4j.msg.WriteMaskRegisterRequest;
import com.serotonin.modbus4j.msg.WriteMaskRegisterResponse;
import com.serotonin.modbus4j.msg.WriteRegisterRequest;
import com.serotonin.modbus4j.msg.WriteRegisterResponse;
import com.serotonin.modbus4j.msg.WriteRegistersRequest;
import com.serotonin.modbus4j.msg.WriteRegistersResponse;

/**
 * 创建者   zgkxzx
 * 创建日期 2017/6/12.
 * 功能描述
 */

public class ModbusReq {

    /**
     * Function Code 1
     * Read Coil Register
     *
     * @param master        ModbusMaster instance
     * @param onRequestBack callback
     * @param slaveId       slave id
     * @param start         start address
     * @param len           length
     */
    public static void readCoil(ModbusMaster master, OnRequestBack<boolean[]> onRequestBack, int slaveId, int start, int len) {
        try {
            ReadCoilsRequest request = new ReadCoilsRequest(slaveId, start, len);
            ReadCoilsResponse response = (ReadCoilsResponse) master.send(request);

            if (response.isException())
                onRequestBack.onFailed(response.getExceptionMessage());
            else {
                boolean[] booleanData = response.getBooleanData();
                boolean[] resultByte = new boolean[len];
                System.arraycopy(booleanData, 0, resultByte, 0, len);
                onRequestBack.onSuccess(resultByte);
            }

        } catch (ModbusTransportException e) {
            e.printStackTrace();
            onRequestBack.onFailed(e.toString());
        }
    }

    /**
     * Function Code 2
     * Read DiscreteInput Register
     *
     * @param master        ModbusMaster instance
     * @param onRequestBack callback
     * @param slaveId       slave id
     * @param start         start address
     * @param len           length
     */
    public static void readDiscreteInput(ModbusMaster master, OnRequestBack<boolean[]> onRequestBack, int slaveId, int start, int len) {
        try {
            ReadDiscreteInputsRequest request = new ReadDiscreteInputsRequest(slaveId, start, len);
            ReadDiscreteInputsResponse response = (ReadDiscreteInputsResponse) master.send(request);

            if (response.isException())
                onRequestBack.onFailed(response.getExceptionMessage());
            else
            {
                boolean[] booleanData = response.getBooleanData();
                boolean[] resultByte = new boolean[len];
                System.arraycopy(booleanData, 0, resultByte, 0, len);
                onRequestBack.onSuccess(resultByte);
            }
        } catch (ModbusTransportException e) {
            e.printStackTrace();
            onRequestBack.onFailed(e.toString());
        }
    }

    /**
     * Function Code 3
     * Read Holding Registers
     *
     * @param master        ModbusMaster instance
     * @param onRequestBack callback
     * @param slaveId       slave id
     * @param start         start address
     * @param len           length
     */
    public static void readHoldingRegisters(ModbusMaster master, OnRequestBack<short[]> onRequestBack, int slaveId, int start, int len) {
        try {
            ReadHoldingRegistersRequest request = new ReadHoldingRegistersRequest(slaveId, start, len);
            ReadHoldingRegistersResponse response = (ReadHoldingRegistersResponse) master.send(request);

            if (response.isException())
                onRequestBack.onFailed(response.getExceptionMessage());
            else
                onRequestBack.onSuccess(response.getShortData());
        } catch (ModbusTransportException e) {
            e.printStackTrace();
            onRequestBack.onFailed(e.toString());
        }
    }

    /**
     * Function Code 4
     * Read Input Registers
     *
     * @param master        ModbusMaster instance
     * @param onRequestBack callback
     * @param slaveId       slave id
     * @param start         start address
     * @param len           length
     */
    public static void readInputRegisters(ModbusMaster master, OnRequestBack<short[]> onRequestBack, int slaveId, int start, int len) {
        try {
            ReadInputRegistersRequest request = new ReadInputRegistersRequest(slaveId, start, len);
            ReadInputRegistersResponse response = (ReadInputRegistersResponse) master.send(request);

            if (response.isException())
                onRequestBack.onFailed(response.getExceptionMessage());
            else
                onRequestBack.onSuccess(response.getShortData());
        } catch (ModbusTransportException e) {
            e.printStackTrace();
            onRequestBack.onFailed(e.toString());
        }
    }

    /**
     * Function Code 5
     * Write Coil
     *
     * @param master        ModbusMaster instance
     * @param onRequestBack callback
     * @param slaveId       slave id
     * @param offset        offset address
     * @param value         value
     */
    public static void writeCoil(ModbusMaster master, OnRequestBack<String> onRequestBack, int slaveId, int offset, boolean value) {
        try {
            WriteCoilRequest request = new WriteCoilRequest(slaveId, offset, value);
            WriteCoilResponse response = (WriteCoilResponse) master.send(request);

            if (response.isException())
                onRequestBack.onFailed(response.getExceptionMessage());
            else
                onRequestBack.onSuccess("Success");
        } catch (ModbusTransportException e) {
            e.printStackTrace();
            onRequestBack.onFailed(e.toString());
        }
    }

    /**
     * Function Code 15
     * Write Coils
     *
     * @param master        ModbusMaster instance
     * @param onRequestBack callback
     * @param slaveId       slave id
     * @param start         start address
     * @param values        values
     */
    public static void writeCoils(ModbusMaster master, OnRequestBack<String> onRequestBack, int slaveId, int start, boolean[] values) {
        try {
            WriteCoilsRequest request = new WriteCoilsRequest(slaveId, start, values);
            WriteCoilsResponse response = (WriteCoilsResponse) master.send(request);

            if (response.isException())
                onRequestBack.onFailed(response.getExceptionMessage());
            else
                onRequestBack.onSuccess("Success");
        } catch (ModbusTransportException e) {
            e.printStackTrace();
            onRequestBack.onFailed(e.toString());
        }
    }

    /**
     * Function Code 6
     * Write Register
     *
     * @param master        ModbusMaster instance
     * @param onRequestBack callback
     * @param slaveId       slave id
     * @param offset        offset address
     * @param value         value
     */
    public static void writeRegister(ModbusMaster master, OnRequestBack<String> onRequestBack, int slaveId, int offset, int value) {
        try {
            WriteRegisterRequest request = new WriteRegisterRequest(slaveId, offset, value);
            WriteRegisterResponse response = (WriteRegisterResponse) master.send(request);

            if (response.isException())
                onRequestBack.onFailed(response.getExceptionMessage());
            else
                onRequestBack.onSuccess("Success");
        } catch (ModbusTransportException e) {
            e.printStackTrace();
            onRequestBack.onFailed(e.toString());
        }
    }


    /**
     * Function Code 16
     * Write Registers
     *
     * @param master        ModbusMaster instance
     * @param onRequestBack callback
     * @param slaveId       slave id
     * @param start         start address
     * @param values        values
     */
    public static void writeRegisters(ModbusMaster master, OnRequestBack<String> onRequestBack, int slaveId, int start, short[] values) {
        try {
            WriteRegistersRequest request = new WriteRegistersRequest(slaveId, start, values);
            WriteRegistersResponse response = (WriteRegistersResponse) master.send(request);

            if (response.isException())
                onRequestBack.onFailed(response.getExceptionMessage());
            else
                onRequestBack.onSuccess("Success");
        } catch (ModbusTransportException e) {
            e.printStackTrace();
            onRequestBack.onFailed(e.toString());
        }
    }

    /**
     * Function Code 7
     * Read Exceptioin Status
     *
     * @param master        ModbusMaster instance
     * @param onRequestBack callback
     * @param slaveId       slave id
     */
    public static void readExceptionStatus(ModbusMaster master, OnRequestBack<Byte> onRequestBack, int slaveId) {
        try {
            ReadExceptionStatusRequest request = new ReadExceptionStatusRequest(slaveId);
            ReadExceptionStatusResponse response = (ReadExceptionStatusResponse) master.send(request);

            if (response.isException())
                onRequestBack.onFailed(response.getExceptionMessage());
            else
                onRequestBack.onSuccess(response.getExceptionStatus());
        } catch (ModbusTransportException e) {
            e.printStackTrace();
            onRequestBack.onFailed(e.toString());
        }
    }

    /**
     * Function Code 17
     * Report Slave Id
     *
     * @param master        ModbusMaster instance
     * @param onRequestBack callback
     * @param slaveId       slave id
     */
    public static void reportSlaveId(ModbusMaster master, OnRequestBack<byte[]> onRequestBack, int slaveId) {
        try {
            ReportSlaveIdRequest request = new ReportSlaveIdRequest(slaveId);
            ReportSlaveIdResponse response = (ReportSlaveIdResponse) master.send(request);

            if (response.isException())
                onRequestBack.onFailed(response.getExceptionMessage());
            else
                onRequestBack.onSuccess(response.getData());
        } catch (ModbusTransportException e) {
            e.printStackTrace();
            onRequestBack.onFailed(e.toString());
        }
    }

    /**
     * Function Code 22
     * Mask Write 4X Register
     *
     * @param master        ModbusMaster instance
     * @param onRequestBack callback
     * @param slaveId       slave id
     * @param offset        offset address
     * @param and           and
     * @param and           or
     */
    public static void writeMaskRegister(ModbusMaster master, OnRequestBack<String> onRequestBack, int slaveId, int offset, int and, int or) {
        try {
            WriteMaskRegisterRequest request = new WriteMaskRegisterRequest(slaveId, offset, and, or);
            WriteMaskRegisterResponse response = (WriteMaskRegisterResponse) master.send(request);

            if (response.isException())
                onRequestBack.onFailed(response.getExceptionMessage());
            else
                onRequestBack.onSuccess("Success");
        } catch (ModbusTransportException e) {
            e.printStackTrace();
            onRequestBack.onFailed(e.toString());
        }
    }
}
