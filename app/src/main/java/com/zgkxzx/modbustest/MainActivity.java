package com.zgkxzx.modbustest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zgkxzx.modbus4And.requset.ModbusParam;
import com.zgkxzx.modbus4And.requset.ModbusReq;
import com.zgkxzx.modbus4And.requset.OnRequestBack;

import java.util.Arrays;

public class MainActivity extends Activity {

    private final static String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        modbusInit();


    }

    private void modbusInit() {

        ModbusReq.getInstance().setParam(new ModbusParam()
                .setHost("192.168.0.105")
                .setPort(502)
                .setEncapsulated(false)
                .setKeepAlive(true)
                .setTimeout(2000)
                .setRetries(0))
                .init(new OnRequestBack<String>() {
            @Override
            public void onSuccess(String s) {
                Log.d(TAG, "onSuccess " + s);
            }

            @Override
            public void onFailed(String msg) {
                Log.d(TAG, "onFailed " + msg);
            }
        });


    }

    public void readCoilClickEvent(View view) {

        ModbusReq.getInstance().readCoil(new OnRequestBack<boolean[]>() {
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

    public void readDiscreteInputClickEvent(View view) {

        ModbusReq.getInstance().readDiscreteInput(new OnRequestBack<boolean[]>() {
            @Override
            public void onSuccess(boolean[] booleen) {
                Log.d(TAG, "readDiscreteInput onSuccess " + Arrays.toString(booleen));
            }

            @Override
            public void onFailed(String msg) {
                Log.e(TAG, "readDiscreteInput onFailed " + msg);
            }
        }, 1, 1, 5);


    }

    public void readHoldingRegistersClickEvent(View view) {

        //readHoldingRegisters
        ModbusReq.getInstance().readHoldingRegisters(new OnRequestBack<short[]>() {
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

    public void readInputRegistersClickEvent(View view) {


        ModbusReq.getInstance().readInputRegisters(new OnRequestBack<short[]>() {
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

    public void writeCoilClickEvent(View view) {


        ModbusReq.getInstance().writeCoil(new OnRequestBack<String>() {
            @Override
            public void onSuccess(String s) {
                Log.e(TAG, "writeCoil onSuccess " + s);
            }

            @Override
            public void onFailed(String msg) {
                Log.e(TAG, "writeCoil onFailed " + msg);
            }
        }, 1, 1, true);


    }

    public void writeRegisterClickEvent(View view) {

        ModbusReq.getInstance().writeRegister(new OnRequestBack<String>() {
            @Override
            public void onSuccess(String s) {
                Log.e(TAG, "writeRegister onSuccess " + s);
            }

            @Override
            public void onFailed(String msg) {
                Log.e(TAG, "writeRegister onFailed " + msg);
            }
        }, 1, 1, 234);


    }

    public void writeRegistersClickEvent(View view) {

        ModbusReq.getInstance().writeRegisters(new OnRequestBack<String>() {
            @Override
            public void onSuccess(String s) {
                Log.e(TAG, "writeRegisters onSuccess " + s);
            }

            @Override
            public void onFailed(String msg) {
                Log.e(TAG, "writeRegisters onFailed " + msg);
            }
        }, 1, 2, new short[]{211, 52, 34});


    }


}
