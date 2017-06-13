package com.zgkxzx.modbustest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.modbus4j.requset.ModbusReq;
import com.serotonin.modbus4j.requset.OnRequestBack;

import java.util.Arrays;

public class MainActivity extends Activity {

    private final static String TAG = MainActivity.class.getSimpleName();

    private ModbusMaster mModbusMaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        modbusInit();


    }

    private void modbusInit() {
        ModbusFactory mModbusFactory = new ModbusFactory();
        IpParameters params = new IpParameters();

        params.setHost("192.168.0.104");
        params.setPort(502);
        params.setEncapsulated(false);

        mModbusMaster = mModbusFactory.createTcpMaster(params, true);
        mModbusMaster.setRetries(4);
        mModbusMaster.setTimeout(2000);
        mModbusMaster.setRetries(0);
        try {
            mModbusMaster.init();
        }catch (ModbusInitException e){
            mModbusMaster.destroy();
        }

    }

    public void readCoilClickEvent(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ModbusReq.readCoil(mModbusMaster, new OnRequestBack<boolean[]>() {
                    @Override
                    public void onSuccess(boolean[] booleen) {
                        Log.d(TAG, "readCoil onSuccess " + Arrays.toString(booleen));
                    }

                    @Override
                    public void onFailed(String msg) {
                        Log.e(TAG, "readCoil onFailed " + msg);
                    }
                }, 1, 1, 2);
            }
        }).start();
    }

    public void readDiscreteInputClickEvent(View view)  {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ModbusReq.readDiscreteInput(mModbusMaster, new OnRequestBack<boolean[]>() {
                    @Override
                    public void onSuccess(boolean[] booleen) {
                        Log.d(TAG, "readDiscreteInput onSuccess " + Arrays.toString(booleen));
                    }

                    @Override
                    public void onFailed(String msg) {
                        Log.e(TAG, "readDiscreteInput onFailed " + msg);
                    }
                },1,1,5);
            }
        }).start();

    }

    public void readHoldingRegistersClickEvent(View view)  {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //readHoldingRegisters
                ModbusReq.readHoldingRegisters(mModbusMaster, new OnRequestBack<short[]>() {
                    @Override
                    public void onSuccess(short[] data) {
                        Log.d(TAG, "readHoldingRegisters onSuccess " + Arrays.toString(data));
                    }

                    @Override
                    public void onFailed(String msg) {
                        Log.e(TAG, "readHoldingRegisters onFailed " + msg);
                    }
                }, 1, 2, 8);
            }
        }).start();

    }

    public void readInputRegistersClickEvent(View view)  {
        new Thread(new Runnable() {
            @Override
            public void run() {

                ModbusReq.readInputRegisters(mModbusMaster, new OnRequestBack<short[]>() {
                    @Override
                    public void onSuccess(short[] data) {
                        Log.d(TAG, "readInputRegisters onSuccess " + Arrays.toString(data));
                    }

                    @Override
                    public void onFailed(String msg) {
                        Log.e(TAG, "readInputRegisters onFailed " + msg);
                    }
                }, 1, 2, 8);
            }
        }).start();
    }

    public void writeCoilClickEvent(View view)  {
        new Thread(new Runnable() {
            @Override
            public void run() {

                ModbusReq.writeCoil(mModbusMaster, new OnRequestBack<String>() {
                    @Override
                    public void onSuccess(String s) {
                        Log.e(TAG, "writeCoil onSuccess " + s);
                    }

                    @Override
                    public void onFailed(String msg) {
                        Log.e(TAG, "writeCoil onFailed " + msg);
                    }
                },1,1,true);
            }
        }).start();
    }

    public void writeRegisterClickEvent(View view)  {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ModbusReq.writeRegister(mModbusMaster, new OnRequestBack<String>() {
                    @Override
                    public void onSuccess(String s) {
                        Log.e(TAG, "writeRegister onSuccess " + s);
                    }

                    @Override
                    public void onFailed(String msg) {
                        Log.e(TAG, "writeRegister onFailed " + msg);
                    }
                },1,1,234);
            }
        }).start();
    }

    public void writeRegistersClickEvent(View view)  {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ModbusReq.writeRegisters(mModbusMaster, new OnRequestBack<String>() {
                    @Override
                    public void onSuccess(String s) {
                        Log.e(TAG, "writeRegisters onSuccess " + s);
                    }

                    @Override
                    public void onFailed(String msg) {
                        Log.e(TAG, "writeRegisters onFailed " + msg);
                    }
                },1,2,new short[]{211,52,34});
            }
        }).start();
    }


}
