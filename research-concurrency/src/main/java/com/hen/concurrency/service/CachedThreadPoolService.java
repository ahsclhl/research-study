package com.hen.concurrency.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hen.concurrency.thread.LoggerRunnable;
import com.hen.utils.NamedThreadFactory;

/**
 * @author liaohenry
 * @version 2016年8月19日 下午5:07:48 
 * 基于 Executors 框架实现 cached thread pool
 * newCachedThreadPool: 两个特点
 * 1.corePoolSize = 0, 因此不会把Thread 放到queue中，然后让消费者consume，因此放进去多少thread，就会立刻执行多少thread，因此queue 是unblocking queue
 * 2.cache 存活的thread 对象，如果该对象在60s 内再次被加入threadPool，则不会新建Thread，使用存活的Thread；
 * 	 如果该Thread 对象已经Running，则新建对象
 */
@Service
public class CachedThreadPoolService {
	private static final Logger logger = LoggerFactory.getLogger(CachedThreadPoolService.class);

	public void cacheService() {
		ExecutorService executorService = Executors.newCachedThreadPool(new NamedThreadFactory("CACHED-POOL"));
		Runnable loggerThread = new LoggerRunnable();
		
		for (int i = 0; i < 3; i++) {
			executorService.submit(loggerThread);
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				logger.info("{}", e);
			}
		}
		executorService.shutdown();
		executorService.shutdownNow();
	}
}
