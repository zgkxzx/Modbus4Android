/**
 * Copyright (C) 2015 Infinite Automation Software. All rights reserved.
 * @author Terry Packer
 */
package com.zgkxzx.modbus4And.sero.epoll;

import java.io.InputStream;

/**
 * @author Terry Packer
 *
 */
public interface InputStreamEPollWrapper {

	/**
	 * @param in
	 * @param inputStreamCallback
	 */
	void add(InputStream in, Modbus4JInputStreamCallback inputStreamCallback);

	/**
	 * @param in
	 */
	void remove(InputStream in);

}
