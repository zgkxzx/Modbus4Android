package com.zgkxzx.modbustest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.code.RegisterRange;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.modbus4j.locator.BaseLocator;
import com.serotonin.modbus4j.locator.NumericLocator;

public class MainActivity extends Activity {

    private final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                modbusTest();
            }
        }).start();

    }

    private void modbusTest() {
        ModbusFactory factory = new ModbusFactory();
        IpParameters params = new IpParameters();

        //        params.setHost("127.0.0.1");
        //        params.setPort(502);
        //        params.setEncapsulated(true);

        params.setHost("192.168.0.101");
        params.setPort(502);
        params.setEncapsulated(false);

        ModbusMaster master = factory.createTcpMaster(params, true);
        // master.setRetries(4);
        master.setTimeout(2000);
        master.setRetries(0);

        long start = System.currentTimeMillis();
        try {
            master.init();
            for (int i = 0; i < 9; i++) {
                Number value = master.getValue(new NumericLocator(1, RegisterRange.HOLDING_REGISTER, i,
                        DataType.TWO_BYTE_INT_SIGNED));
                Log.e(TAG, " i : " + i + " --> Register: " + value);
                BaseLocator<Number> locator = BaseLocator.holdingRegister(1, 50, DataType.EIGHT_BYTE_INT_UNSIGNED);
                master.setValue(locator, 10000000);

            }
        } catch (Exception e) {
            Log.e(TAG, " exception : " + e.toString());
        } finally {
            master.destroy();
        }

        System.out.println("Took: " + (System.currentTimeMillis() - start) + "ms");
    }


}
