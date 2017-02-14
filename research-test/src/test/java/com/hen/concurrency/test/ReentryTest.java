package com.hen.concurrency.test;

import org.junit.Test;

import com.hen.concurrency.lock.ReentryLockService;

/** 
* @author  liaohenry
* @version 2016年12月7日 下午3:46:38
*/
public class ReentryTest {
	
	/*
	 * 
2016-12-07 18:49:16.380 [Thread-1] INFO c.h.c.lock.ReentryLockService:34 - run lockNonStatic -- Thread[Thread-1,5,main]
2016-12-07 18:49:16.380 [Thread-0] INFO c.h.c.lock.ReentryLockService:34 - run lockNonStatic -- Thread[Thread-0,5,main]
2016-12-07 18:49:18.383 [Thread-1] INFO c.h.c.lock.ReentryLockService:40 - end lockNonStatic -- Thread[Thread-1,5,main]
2016-12-07 18:49:18.384 [Thread-0] INFO c.h.c.lock.ReentryLockService:40 - end lockNonStatic -- Thread[Thread-0,5,main]
	 */
//	@Test
	public void testLockNonStatic() {
		Thread thread1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				ReentryLockService service1 = new ReentryLockService();
				service1.lockNonStatic();
			}
		});
		
		Thread thread2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				ReentryLockService service1 = new ReentryLockService();
				service1.lockNonStatic();
			}
		});
		
		thread1.start();
		thread2.start();
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * 
2016-12-07 18:50:17.520 [Thread-0] INFO c.h.c.lock.ReentryLockService:20 - run lockStatic -- Thread[Thread-0,5,main]
2016-12-07 18:50:19.523 [Thread-0] INFO c.h.c.lock.ReentryLockService:26 - end lockStatic -- Thread[Thread-0,5,main]
2016-12-07 18:50:19.523 [Thread-1] INFO c.h.c.lock.ReentryLockService:20 - run lockStatic -- Thread[Thread-1,5,main]
2016-12-07 18:50:21.524 [Thread-1] INFO c.h.c.lock.ReentryLockService:26 - end lockStatic -- Thread[Thread-1,5,main]
	 */
	@Test
	public void testLockStatic() {
		Thread thread1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				ReentryLockService service1 = new ReentryLockService();
				service1.lockStatic();
			}
		});
		
		Thread thread2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				ReentryLockService service1 = new ReentryLockService();
				service1.lockStatic();
			}
		});
		
		thread1.start();
		thread2.start();
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
