## Modbus For Android

### Introduce
This is a Modbus library for Android.

### How to use
#### 1.Add the library to your project

	compile files('libs/modbus4Android-1.0.jar')
Copy the jar to the lib directory of your project and add the config to build.gradle.
#### 2.Create and init ModbusReq instance

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

Init ModbusReq instance through setting the modbus param.
#### 3.Read Coil

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

<img src="https://github.com/zgkxzx/Modbus4Android/blob/master/screenshot/read_coil.png" width = "1353" height = "500" alt="截屏" align=center />

#### 4.Read DiscreteInput
	
	ModbusReq.getInstance().readDiscreteInput(new OnRequestBack<boolean[]>() {
                        @Override
                        public void onSuccess(boolean[] booleen) {
                            Log.d(TAG, "readDiscreteInput onSuccess " + Arrays.toString(booleen));
                        }
    
                        @Override
                        public void onFailed(String msg) {
                            Log.e(TAG, "readDiscreteInput onFailed " + msg);
                        }
                    },1,1,5);

<img src="https://github.com/zgkxzx/Modbus4Android/blob/master/screenshot/read_discrete_input.png" width = "1353" height = "500" alt="截屏" align=center />

#### 5.Read HoldingRegisters
	
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

<img src="https://github.com/zgkxzx/Modbus4Android/blob/master/screenshot/read_holding_register.png" width = "1353" height = "500" alt="截屏" align=center />

#### 6.Read InputRegisters(Child Thread)
	
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

<img src="https://github.com/zgkxzx/Modbus4Android/blob/master/screenshot/read_input_registers.png" width = "1353" height = "500" alt="截屏" align=center />

#### 7.Write Coil
	
	ModbusReq.getInstance().writeCoil(new OnRequestBack<String>() {
                        @Override
                        public void onSuccess(String s) {
                            Log.e(TAG, "writeCoil onSuccess " + s);
                        }
    
                        @Override
                        public void onFailed(String msg) {
                            Log.e(TAG, "writeCoil onFailed " + msg);
                        }
                    },1,1,true);

<img src="https://github.com/zgkxzx/Modbus4Android/blob/master/screenshot/write_coil.png" width = "1353" height = "500" alt="截屏" align=center />

#### 8.Write Register
	
	ModbusReq.getInstance().writeRegister(new OnRequestBack<String>() {
                        @Override
                        public void onSuccess(String s) {
                            Log.e(TAG, "writeRegister onSuccess " + s);
                        }
    
                        @Override
                        public void onFailed(String msg) {
                            Log.e(TAG, "writeRegister onFailed " + msg);
                        }
                    },1,1,234);

<img src="https://github.com/zgkxzx/Modbus4Android/blob/master/screenshot/write_register.png" width = "1353" height = "500" alt="截屏" align=center />

#### 9.Write Registers
	
	ModbusReq.getInstance().writeRegisters(new OnRequestBack<String>() {
                        @Override
                        public void onSuccess(String s) {
                            Log.e(TAG, "writeRegisters onSuccess " + s);
                        }
    
                        @Override
                        public void onFailed(String msg) {
                            Log.e(TAG, "writeRegisters onFailed " + msg);
                        }
                    },1,2,new short[]{211,52,34});

<img src="https://github.com/zgkxzx/Modbus4Android/blob/master/screenshot/write_registers.png" width = "1353" height = "500" alt="截屏" align=center />

#### 10.Destroy Modbus Instance

	ModbusReq.getInstance().destroy();

### Extends
if you use RTU Mode with SerialPort,you should do :

- 1. Create a class to implement SerialPortWrapper interface.
- 2. Create a ModbusMaster instance with createRtuMaster mothod of ModbusFactory class.
	
### Feedback

You have any questions to send email to me.Thanks.

* e-mail : zgkxzx@163.com
* gmail  : my.own.diy@gmail.com

### Me & Throught

#### Simplicity is not simple.
	
