package com.hen.dubbo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/** 
* @author  liaohenry
* @version 2016年8月12日 下午5:47:59
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"classpath:spring/common-context.xml", 
		"classpath:spring/dubbo-provider-context.xml"
})
public class DubboProviderTest {

	private static final Logger logger = LoggerFactory.getLogger(DubboProviderTest.class);
	
	@Test
	public void testHello() {
		logger.info("starting");
	}
}
