package com.hen.concurrency.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* @author  liaohenry
* @version 2016年8月16日 下午2:30:23
*/
public class MyThreadPool extends ThreadPoolExecutor {

	private static final Logger logger = LoggerFactory.getLogger(MyThreadPool.class);
	
	public MyThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}
	
	@Override
	public void shutdown() {
		logger.info("shutdown");
		logger.debug("Size of queue: {}", getQueue().size());
	}
	
	@Override
	public void beforeExecute(Thread t, Runnable r) { 
		logger.info("beforeExecute");
		logger.info("Size of queue: {}", this.getQueue().size());
		super.beforeExecute(t, r);
	}
	
	@Override
	public void afterExecute(Runnable r, Throwable t) {
		super.afterExecute(r, t);
//		Future<?> future = (Future<?>) r;
//		try {
//			future.get();
//		} catch (InterruptedException e) {
//			logger.debug("InterruptedException: {}", e);
//		} catch (ExecutionException e) {
//			logger.debug("ExecutionException: {}", e);
//		}
	}

}
