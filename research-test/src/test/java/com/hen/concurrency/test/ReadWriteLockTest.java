package com.hen.concurrency.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hen.concurrency.service.ReadWriteLockService;

/** 
* @author  liaohenry
* @version 2016年8月4日 下午4:59:50
* 测试：读写锁
* 读锁：不排他；写锁：排他
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/common-context.xml",
		"classpath:spring/concurrency-context.xml"
})
public class ReadWriteLockTest {
	private Logger logger = LoggerFactory.getLogger(ReadWriteLockTest.class);
	@Autowired
	private ReadWriteLockService service;
	
	@Test
	public void test() {
		logger.info("start testing");
		service.start();
		logger.info("finish testing");
	}
}
