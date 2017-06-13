## modbus tcp for android

### 介绍
这是Android版的Modbus TCP 例子，例子里面只写了对保持寄存器的读写功能，其中要注意的是对modbus的初始化、读、写等等操作都要在子线程里面进行，不然会引起网络操作在主线程的异常。

### 说明
对于modbus 的线圈，离散寄存器，输入寄存器等等的操作，程序思路基本一致，不另作例子；对于modbus RTU/ASCII模式的通信，手机上面不带串口功能，用不了此功能，暂时没有上传。

### 如何使用
>创建ModbusMaster
	
	ModbusFactory factory = new ModbusFactory();
    IpParameters params = new IpParameters();
	//设置IP地址以及端口号
    params.setHost("192.168.0.101");
    params.setPort(502);
    params.setEncapsulated(false);
	//创建ModbusMaster以及设置超时时间
    ModbusMaster master = factory.createTcpMaster(params, true);
    // master.setRetries(4);
    master.setTimeout(2000);
    master.setRetries(0);

>初始化master(子线程操作),注意这里进行异常处理(子线程操作)

	master.init();

>读取保持寄存器的值(子线程操作)

	Number value = master.getValue(new NumericLocator(1, RegisterRange.HOLDING_REGISTER, i,
                        DataType.TWO_BYTE_INT_SIGNED));
>写入保持寄存器的值(子线程操作)
	
	BaseLocator<Number> locator = BaseLocator.holdingRegister(1, 50, DataType.EIGHT_BYTE_INT_UNSIGNED);
    master.setValue(locator, 10000000);
<img src="https://github.com/qindachang/RulerView/blob/master/imgs/Screenshot_1479577403.png" width = "320" height = "568" alt="截屏" align=center />
>在发生异常或者关闭modbus,均对modbus实例销毁

	master.destroy();
	
### 反馈
由于笔者时间有限和工程的局限性，很多问题并没有那么周全，有什么问题可直接联系
* Email：zgkxzx@163.com
* QQ:514011021

### 我&思想
简单并非简单
	
