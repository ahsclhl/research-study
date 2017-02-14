package com.hen.concurrency.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.hen.concurrency.thread.LoggerRunnable;
import com.hen.utils.NamedThreadFactory;

/** 
* @author  liaohenry
* @version 2016年8月19日 下午5:27:08
* 基于Executors 框架实现 schedule service
*/
@Service
public class ScheduledServiceWithExecutor {
	//private static final Logger logger = LoggerFactory.getLogger(ScheduledServiceWithExecutor.class);
	
	public ScheduledFuture<?> scheduleService() {
		ScheduledExecutorService scheduleService = Executors.newScheduledThreadPool(1, new NamedThreadFactory("SCHEDULE-POOL"));
		ScheduledFuture<?> future1 = scheduleService.scheduleAtFixedRate(new LoggerRunnable("SCHEDUL TASK 1"), 1, 1, TimeUnit.SECONDS);
		//scheduleService.scheduleWithFixedDelay(new LoggerRunnable("SCHDULE TASK 2"), 1, 2, TimeUnit.SECONDS);
		return future1;
	}
}
