package com.hen.concurrency.test;

import java.util.concurrent.ScheduledFuture;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hen.concurrency.service.ScheduledServiceWithExecutor;

/** 
* @author  liaohenry
* @version 2016年8月20日 下午8:34:14
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/common-context.xml",
		"classpath:spring/concurrency-context.xml"
})
public class ScheduleServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(ScheduleServiceTest.class);
	
	@Autowired
	private ScheduledServiceWithExecutor service;
	
	@Test
	public void testScheduleService() {
		ScheduledFuture<?> future = service.scheduleService();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			logger.error("{}", e);
		}
		future.cancel(false);
	}
}
