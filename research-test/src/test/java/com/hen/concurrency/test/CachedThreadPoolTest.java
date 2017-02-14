package com.hen.concurrency.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hen.concurrency.service.CachedThreadPoolService;

/** 
* @author  liaohenry
* @version 2016年8月19日 下午5:54:01
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/common-context.xml",
		"classpath:spring/concurrency-context.xml"
})
public class CachedThreadPoolTest {
	@Autowired
	private CachedThreadPoolService service;
	
	@Test
	public void testCacheService() {
		service.cacheService();
	}
}
