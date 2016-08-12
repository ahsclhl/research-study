package com.hen.zk.curator;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* @author  liaohenry
* @version 2016年8月1日 下午3:01:22
*/
public class Lock {
	private Logger logger = LoggerFactory.getLogger(Lock.class);
	private static final String lock_path = "/locks";
	private CuratorInstance curator;
	
	@SuppressWarnings("static-access")
	public void lock() {
		InterProcessReadWriteLock readWriteLock = new InterProcessReadWriteLock(curator.instance(), lock_path);
		InterProcessMutex readLock = readWriteLock.readLock();
		try {
			readLock.acquire();
		} catch (Exception e) {
			logger.error("{}", e);
		}
	}
}
