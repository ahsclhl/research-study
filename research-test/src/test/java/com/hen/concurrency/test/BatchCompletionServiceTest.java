package com.hen.concurrency.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hen.concurrency.service.BatchCompletionService;

/** 
* @author  liaohenry
* @version 2016年9月19日 下午4:30:18
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/common-context.xml",
		"classpath:spring/concurrency-context.xml"
})
public class BatchCompletionServiceTest {
	@Autowired
	private BatchCompletionService service;
	
	@Test
	public void testComplete() {
		service.completeService();
	}
}
