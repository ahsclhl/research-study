package com.hen.concurrency.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hen.concurrency.bean.BaseVal;
import com.hen.concurrency.thread.ReadThreadLocalService;
import com.hen.concurrency.thread.WriteThreadLocalService;

/** 
* @author  liaohenry
* @version 2016年8月11日 下午5:26:29
* 严重ThreadLocal 的功能：ThreadLocal 并不会为每个变量copy 一份；
* 每个thread 会维护一个ThreadLocalMap： Thread t = Thread.currentThread(); ThreadLocalMap map = getMap(t);
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/common-context.xml",
		"classpath:spring/concurrency-context.xml"
})
public class ThreadLocalTest {
	
	@Test
	public void test() throws InterruptedException {
		BaseVal baseVal = new BaseVal();
		baseVal.setVal("old val");
		
		WriteThreadLocalService writeService = new WriteThreadLocalService(baseVal);
		ReadThreadLocalService readService = new ReadThreadLocalService(baseVal);
		
		Thread thread1 = new Thread(writeService);
		thread1.start();
		Thread.sleep(1000);
		
		Thread thread2 = new Thread(readService);
		thread2.start();
		
		// wait the ending of all threads
		thread1.join();
		thread2.join();
		
	}
}
