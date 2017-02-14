package com.hen.concurrency.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liaohenry
 * @version 2016年8月19日 下午6:24:12
 */
public class LoggerRunnable implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(LoggerRunnable.class);
	private String name;

	public LoggerRunnable() {
		this.name = "NULL";
	}

	public LoggerRunnable(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		logger.info("start");
		logger.info("running " + this.name);
		try {
			Thread.sleep(2000);
			//Thread.sleep(new Random().nextInt(2000));
		} catch (InterruptedException e) {
			logger.error("{}", e);
		}
		logger.info("end");
	}

}
