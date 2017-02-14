package com.hen.concurrency.lock;

import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* @author  liaohenry
* @version 2016年12月7日 下午3:24:34
*/
public class ReentryLockService {
	private static Logger logger = LoggerFactory.getLogger(ReentryLockService.class);
	private ReentrantLock lock = new ReentrantLock(true);
	private static ReentrantLock staticLock = new ReentrantLock(true);
	
	public void lockStatic() {
		try {
			staticLock.lock();
			logger.info("run lockStatic -- " + Thread.currentThread());
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			staticLock.unlock();
			logger.info("end lockStatic -- " + Thread.currentThread());
		}
		
	}
	
	public void lockNonStatic() {
		try {
			lock.lock();
			logger.info("run lockNonStatic -- " + Thread.currentThread());
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
			logger.info("end lockNonStatic -- " + Thread.currentThread());
		}
		
	}
}
