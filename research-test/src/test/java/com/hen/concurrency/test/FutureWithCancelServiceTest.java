package com.hen.concurrency.test;

import java.util.LinkedList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Future;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hen.concurrency.service.FutureWithCancelService;

/** 
* @author  liaohenry
* cancel 实际上 = interrupt，只能打断线程，而不能立刻停止线程
* @version 2016年8月20日 下午9:12:32
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/common-context.xml",
		"classpath:spring/concurrency-context.xml"
})
public class FutureWithCancelServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(FutureWithCancelServiceTest.class);
	@Autowired
	private FutureWithCancelService service;
	
	@Test
	public void testService() {
		Future<Void> future = service.service();
		logger.info("start service");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			logger.error("{}", e);
		}
		boolean cancelled = future.cancel(true);
		logger.info("cancel task: {}", cancelled);
		
	}
}
