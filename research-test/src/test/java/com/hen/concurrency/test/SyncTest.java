package com.hen.concurrency.test;

import org.junit.Test;

import com.hen.concurrency.lock.SynchronizedService;

/**
 * @author liaohenry
 * @version 2016年12月7日 上午10:11:01
 * synchronized 可以修饰：method -- synchronized(this); block--基于variable; Class - synchronized(SyncTestEntry.class)
 * method, synchronized(this), synchronized(non-static variable) 都是基于instance 的同步
 * synchronized(SyncTestEntry.class); synchronized (static variable) 是一种序列化的操作，对Class 的同步
 */
public class SyncTest {

	private SynchronizedService syncMethod = new SynchronizedService();
	/*
	 * 对method 的同步理论是是对this 的同步，同下。
	 * 不同的instance ，不会同步
	 * 同一个instance ，会同步
	 */
	 @Test
	public void testSyncMethod() {
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				syncMethod.syncMethod();
			}
		});

		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				syncMethod.syncMethod2();
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
	 * 不同的instance， 不会被同步
	 */
	// @Test
	public void testSyncMethodWithInstance() {
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				syncMethod.syncMethod();
			}
		});

		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				syncMethod.syncMethod2();
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
	 * 不同的instance lock，不会同步
	 */
	// @Test
	public void testSyncBlock() {
		
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				syncMethod.syncBlock();
			}
		});

		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				syncMethod.syncBlock();
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
	 * 2016-12-07 11:03:02.480 [Thread-0] INFO c.h.c.lock.SynchronizedTest:53 -
	 * run syncStaticBlock1 -- Thread[Thread-0,5,main] 2016-12-07 11:03:04.484
	 * [Thread-0] INFO c.h.c.lock.SynchronizedTest:59 - end syncStaticBlock1 --
	 * Thread[Thread-0,5,main] 2016-12-07 11:03:04.485 [Thread-1] INFO
	 * c.h.c.lock.SynchronizedTest:53 - run syncStaticBlock1 --
	 * Thread[Thread-1,5,main] 2016-12-07 11:03:06.490 [Thread-1] INFO
	 * c.h.c.lock.SynchronizedTest:59 - end syncStaticBlock1 --
	 * Thread[Thread-1,5,main] 
	 * 
	 * 不同的block ，因为对统一static 变量同步，所以不同的block 也是同步了
	 */
//	@Test
	public void testSyncStaticBlock() {
		Thread thread1 = new Thread(new Runnable() {
			private SynchronizedService syncMethod1 = new SynchronizedService();
			@Override
			public void run() {
				
				syncMethod1.syncStaticBlock1();
			}
		});

		Thread thread2 = new Thread(new Runnable() {
			private SynchronizedService syncMethod2 = new SynchronizedService();
			@Override
			public void run() {
				
				syncMethod2.syncStaticBlock2();
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
	 * 2016-12-07 10:52:30.055 [Thread-1] INFO c.h.c.lock.SynchronizedTest:75 -
	 * run syncStaticFunction2 -- Thread[Thread-1,5,main] 2016-12-07
	 * 10:52:30.055 [Thread-0] INFO c.h.c.lock.SynchronizedTest:65 - run
	 * syncStaticFunction1 -- Thread[Thread-0,5,main] 2016-12-07 10:52:32.059
	 * [Thread-0] INFO c.h.c.lock.SynchronizedTest:71 - end syncStaticFunction1
	 * -- Thread[Thread-0,5,main] 2016-12-07 10:52:32.059 [Thread-1] INFO
	 * c.h.c.lock.SynchronizedTest:81 - end syncStaticFunction2 --
	 * Thread[Thread-1,5,main]
	 * 
	 * 不同 static function , 是不会同步的
	 */
	// @Test
	public void testSyncStaticFunction() {
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				syncMethod.syncStaticFunction1();
			}
		});

		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				syncMethod.syncStaticFunction2();
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
	 * class 同步，即使不同的class instance 也会被同步。类似 serializable，序列化的执行
2016-12-07 11:10:27.741 [Thread-0] INFO c.h.c.lock.SynchronizedTest:99 - run syncClass1 -- Thread[Thread-0,5,main]
2016-12-07 11:10:29.744 [Thread-0] INFO c.h.c.lock.SynchronizedTest:105 - end syncClass1 -- Thread[Thread-0,5,main]
2016-12-07 11:10:29.745 [Thread-1] INFO c.h.c.lock.SynchronizedTest:111 - run syncClass2 -- Thread[Thread-1,5,main]
2016-12-07 11:10:31.747 [Thread-1] INFO c.h.c.lock.SynchronizedTest:117 - end syncClass2 -- Thread[Thread-1,5,main]
	 */
//	@Test
	public void testSyncClass() {
		Thread thread1 = new Thread(new Runnable() {
			private SynchronizedService syncMethod1 = new SynchronizedService();
			@Override
			public void run() {
				syncMethod1.syncClass1();
			}
		});

		Thread thread2 = new Thread(new Runnable() {
			private SynchronizedService syncMethod2 = new SynchronizedService();
			@Override
			public void run() {
				syncMethod2.syncClass2();
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
