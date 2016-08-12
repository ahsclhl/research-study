package com.hen.concurrency.lock;

import java.util.concurrent.locks.ReentrantLock;

/** 
* @author  liaohenry
* @version 2016年8月4日 下午5:21:03
*/
public class ReentryJob {
//	private Logger logger = LoggerFactory.getLogger(ReentryJob.class);
	
	private ReentrantLock lock;
	
	public void work() {
		lock.lock();
		

	}
}
