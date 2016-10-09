###modbus tcp for android
有问题请咨询QQ:514011021

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

>初始化master,注意这里进行异常处理

	master.init();

>读取保持寄存器的值

	Number value = master.getValue(new NumericLocator(1, RegisterRange.HOLDING_REGISTER, i,
                        DataType.TWO_BYTE_INT_SIGNED));
>写入保持寄存器的值
	
	BaseLocator<Number> locator = BaseLocator.holdingRegister(1, 50, DataType.EIGHT_BYTE_INT_UNSIGNED);
    master.setValue(locator, 10000000);

>在发生异常或者关闭modbus,均对modbus实例销毁

	master.destroy();
	