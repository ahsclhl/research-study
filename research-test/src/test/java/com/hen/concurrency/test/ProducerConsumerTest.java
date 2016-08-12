package com.hen.concurrency.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hen.concurrency.service.ProduceConsumeService;

/** 
* @author  liaohenry
* @version 2016年8月4日 下午3:49:39
* 测试Produce - Consume 模式，基于：synchronize, await, notify 实现
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/common-context.xml",
		"classpath:spring/concurrency-context.xml"
})
public class ProducerConsumerTest {
	private Logger logger = LoggerFactory.getLogger(ProducerConsumerTest.class);
	@Autowired
	private ProduceConsumeService service;
	
	@Test
	public void test() {
		logger.info("start testing");
		service.start();
		logger.info("finish tesing");
	}
}
