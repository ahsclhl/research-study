package com.hen.concurrency.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author liaohenry
 * @version 2016年8月16日 下午3:24:37
 */
public class MyExecutorService implements InitializingBean, DisposableBean {

	private static final Logger logger = LoggerFactory.getLogger(MyExecutorService.class);
	private static final Integer PROCESSERS = Runtime.getRuntime().availableProcessors();
	private final ExecutorService executorService = new MyThreadPool(PROCESSERS, PROCESSERS * 60, 60, TimeUnit.SECONDS,
			new LinkedBlockingDeque<Runnable>());

	@Override
	public void destroy() throws Exception {
		executorService.shutdown();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("MyExecutorService initialized with PROCESSERS: {}", PROCESSERS);
	}

	public ExecutorService getExecutorService() {
		return executorService;
	}

}
