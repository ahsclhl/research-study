package com.hen.concurrency.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liaohenry
 * @version 2016年12月7日 上午9:15:15
 */
public class SynchronizedService {
	private Logger logger = LoggerFactory.getLogger(SynchronizedService.class);
	private byte[] lock = new byte[0];
	private static byte[] staticLock = new byte[0];

	public synchronized void syncMethod() {
		logger.info("run syncMethod -- " + Thread.currentThread());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("end syncMethod -- " + Thread.currentThread());
	}

	public void syncMethod2() {
		synchronized (this) {
			logger.info("run syncMethod2 -- " + Thread.currentThread());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			logger.info("end syncMethod2 -- " + Thread.currentThread());
		}

	}

	public void syncBlock() {
		synchronized (lock) {
			logger.info("run syncBlock -- " + Thread.currentThread());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			logger.info("end syncBlock -- " + Thread.currentThread());
		}

	}

	public void syncStaticBlock1() {
		synchronized (staticLock) {
			logger.info("run syncStaticBlock1 -- " + Thread.currentThread());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			logger.info("end syncStaticBlock1 -- " + Thread.currentThread());
		}

	}
	
	public void syncStaticBlock2() {
		synchronized (staticLock) {
			logger.info("run syncStaticBlock2 -- " + Thread.currentThread());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			logger.info("end syncStaticBlock2 -- " + Thread.currentThread());
		}

	}

	public synchronized void syncStaticFunction1() {
		logger.info("run syncStaticFunction1 -- " + Thread.currentThread());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("end syncStaticFunction1 -- " + Thread.currentThread());
	}

	public synchronized void syncStaticFunction2() {
		logger.info("run syncStaticFunction2 -- " + Thread.currentThread());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("end syncStaticFunction2 -- " + Thread.currentThread());
	}
	
	public void syncClass1() {
		synchronized (SynchronizedService.class) {
			logger.info("run syncClass1 -- " + Thread.currentThread());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			logger.info("end syncClass1 -- " + Thread.currentThread());
		}
	}
	
	public void syncClass2() {
		synchronized (SynchronizedService.class) {
			logger.info("run syncClass2 -- " + Thread.currentThread());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			logger.info("end syncClass2 -- " + Thread.currentThread());
		}
	}
}
