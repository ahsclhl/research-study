package com.hen.concurrency.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liaohenry
 * @version 2016年8月4日 下午4:46:58
 */
public class ReadlJob implements Runnable {
	private Logger logger = LoggerFactory.getLogger(ReadlJob.class);
	private ReentrantReadWriteLock lock;
	private int num;

	public ReadlJob(ReentrantReadWriteLock lock, int num) {
		this.lock = lock;
		this.num = num;
	}

	@Override
	public void run() {
		Thread.currentThread().setName("ReadlJob " + this.num);
		while (true) {
			try {
				logger.info("start");
				lock.readLock().lock();

				logger.info("Reading info");

				Thread.sleep(1000);
			} catch (InterruptedException e) {
				logger.error("{}", e);
			} finally {
				lock.readLock().unlock();
			}

		}

	}

}
