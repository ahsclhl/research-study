package com.hen.concurrency.thread;

import java.util.Random;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* @author  liaohenry
* @version 2016年8月20日 下午8:52:30
*/
public class LoggerCallable implements Callable<Void> {
	private static final Logger logger = LoggerFactory.getLogger(LoggerCallable.class);
	private String name;

	public LoggerCallable() {
		this.name = "NULL";
	}

	public LoggerCallable(String name) {
		this.name = name;
	}
	@Override
	public Void call() throws Exception {
		logger.info("start");
		logger.info("running " + this.name);
		try {
//			Thread.sleep(2000);
			Thread.sleep(new Random().nextInt(2000));
		} catch (InterruptedException e) {
			logger.error("{}", e);
		}
		logger.info("end");
		return null;
	}

}
