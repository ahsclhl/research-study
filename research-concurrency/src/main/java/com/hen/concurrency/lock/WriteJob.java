package com.hen.concurrency.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liaohenry
 * @version 2016年8月4日 下午4:51:23
 * ReentrantReadWriteLock 和 ReentryLock 是完全不同的接口
 */
public class WriteJob implements Runnable {
	private Logger logger = LoggerFactory.getLogger(WriteJob.class);
	private ReentrantReadWriteLock lock;
	private int num;

	public WriteJob(ReentrantReadWriteLock lock, int num) {
		this.lock = lock;
		this.num = num;
	}

	@Override
	public void run() {
		Thread.currentThread().setName("WriteJob " + this.num);
		while (true) {
			try {
				logger.info("start");
				lock.writeLock().lock();

				logger.info("Writing info");

				Thread.sleep(2000);
			} catch (InterruptedException e) {
				logger.error("{}", e);
			} finally {
				lock.writeLock().unlock();
			}

		}

	}

}
