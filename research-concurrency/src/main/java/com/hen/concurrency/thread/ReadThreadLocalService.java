package com.hen.concurrency.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hen.concurrency.bean.BaseVal;

/** 
* @author  liaohenry
* @version 2016年8月11日 下午4:54:11
*/
public class ReadThreadLocalService implements Runnable {
	private Logger logger = LoggerFactory.getLogger(ReadThreadLocalService.class);
	
	private final BaseVal val;
	
	public ReadThreadLocalService(BaseVal val) {		
		this.val = val;
	}

	@Override
	public void run() {
		Thread.currentThread().setName("-ReadThread-");
		ThreadLocal<BaseVal> localVal = new ThreadLocal<>();
		localVal.set(val);
		logger.info("read local value: {}", localVal.get());

	}

}
