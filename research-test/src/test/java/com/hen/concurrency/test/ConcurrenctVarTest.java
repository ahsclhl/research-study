package com.hen.concurrency.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/** 
* @author  liaohenry
* @version 2017年1月16日 下午10:23:16
*/
public class ConcurrenctVarTest {
	
	public void testSemaphore() {
		Semaphore semaphore = new Semaphore(10);
		try {
			semaphore.acquire();
			
			semaphore.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void testCountDownLatch() {
		CountDownLatch latch = new CountDownLatch(10);
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testCyclicBarrier() {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
	}
}
