package com.hen.job.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/** 
* @author  liaohenry
* @version 2016年8月3日 上午10:43:20
* 测试elastic-job ThroughPut job
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/common-context.xml", 
		"classpath:spring/dao-context.xml",
		"classpath:spring/job-context.xml"
})
public class ThroughputJobTest {

	private Logger logger = LoggerFactory.getLogger(ThroughputJobTest.class);
	
	@Test
	public void jobTest() {
		logger.info("job start");
		try {
			Thread.sleep(600000);
		} catch (InterruptedException e) {
			logger.error("{}", e);
		}
		logger.info("job end");
	}
}
