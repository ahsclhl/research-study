package com.hen.concurrency.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hen.concurrency.lock.ReadlJob;
import com.hen.concurrency.lock.WriteJob;

/** 
* @author  liaohenry
* @version 2016年8月4日 下午4:52:53
*/
@Service
public class ReadWriteLockService {
	private Logger logger = LoggerFactory.getLogger(ReadWriteLockService.class);
	
	public void start() {
		logger.info("start");
		ExecutorService pool = Executors.newFixedThreadPool(5);
		ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
		for (int i = 1; i < 5; i++) {
			pool.submit(new ReadlJob(lock, i));
		}
		
		for (int i = 1; i < 2; i++) {
			pool.submit(new WriteJob(lock, i));
		}
		pool.shutdown();
		
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			logger.error("{}", e);
		}
	}
}
