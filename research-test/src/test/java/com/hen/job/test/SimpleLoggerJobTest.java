package com.hen.job.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author liaohenry
 * @version 2016年7月29日 下午3:36:36
 * 测试elastic-job SimpleJob
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"classpath:spring/common-context.xml", 
		"classpath:spring/job-context.xml" 
})
public class SimpleLoggerJobTest {
	private Logger logger = LoggerFactory.getLogger(SimpleLoggerJobTest.class);
	
	@Test
	public void jobTest() {
		logger.info("job start");
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			logger.error("{}", e);
		}
		logger.info("job end");
	}
}
