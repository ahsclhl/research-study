package com.hen.zk.curator;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 
* @author  liaohenry
* @version 2016年8月11日 上午10:45:13
* 测试Curator LeaderLatch：如果没有拿到Leadership，则block
*/
@Service
public class LeaderLectionService {
	private Logger logger = LoggerFactory.getLogger(Lock.class);
	private static final String lock_path = "/leader";
	private CuratorInstance curator;
	
	@SuppressWarnings({ "static-access", "resource" })
	public void leader() throws Exception {
		LeaderLatch leaderLatch = new LeaderLatch(curator.instance(), lock_path);
		try {
			
			logger.info("start leader lection");
			leaderLatch.start();
			logger.info("wait leader ship");
			leaderLatch.await(5, TimeUnit.SECONDS);
			logger.info("end the waiting");
		} finally {
//			leaderLatch.close();
		}
		
	}
}
