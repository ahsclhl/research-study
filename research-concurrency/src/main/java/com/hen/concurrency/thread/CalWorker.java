package com.hen.concurrency.thread;

import java.util.Random;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liaohenry
 * @version 2016年9月19日 下午4:19:05
 */
public class CalWorker implements Callable<Integer> {
	private static final Logger logger = LoggerFactory.getLogger(Callable.class);
	private int id;
	public CalWorker(int id) {
		super();
		this.id = id;
	}
	
	@Override
	public Integer call() throws Exception {
		logger.info("start");
		int val = new Random().nextInt(10);
		logger.info("value is :{}", val);
		Thread.sleep(val * 1000);
//		for(int i=val; i>0 ; i--)
//			;
		logger.info("end");
		return val;
	}

}
