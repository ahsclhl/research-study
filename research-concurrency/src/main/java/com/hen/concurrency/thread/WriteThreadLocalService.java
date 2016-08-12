package com.hen.concurrency.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hen.concurrency.bean.BaseVal;

/** 
* @author  liaohenry
* @version 2016年8月11日 下午4:35:20
*/
public class WriteThreadLocalService implements Runnable {
	private Logger logger = LoggerFactory.getLogger(WriteThreadLocalService.class);
	
	private BaseVal val;
	
	public WriteThreadLocalService(BaseVal val) {		
		this.val = val;
	}

	@Override
	public void run() {
		Thread.currentThread().setName("-WriteThread-");
		ThreadLocal<BaseVal> localVal = new ThreadLocal<>();
		localVal.set(val);
		logger.info("the old value is : {}", localVal.get());
		logger.info("write into local value");
		localVal.get().setVal("new_val");
		
	}

}
