package com.hen.zk.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hen.zk.curator.LeaderLectionService;
import com.hen.zk.curator.Lock;

/**
 * @author liaohenry
 * @version 2016年8月11日 上午11:38:21
 * 测试Curator LeaderLatch：如果没有拿到Leadership，则block
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/common-context.xml",
		"classpath:spring/zk-context.xml"
})
public class LeaderLectionServiceTest {
	private Logger logger = LoggerFactory.getLogger(Lock.class);
	@Autowired
	private LeaderLectionService service;

	@Test
	public void testLeader() throws Exception {
		final int THREAD_NUM = 3;
		ExecutorService executorService = Executors.newFixedThreadPool(THREAD_NUM);
		ExecutorCompletionService<Void> completionService = new ExecutorCompletionService<>(executorService);
		
		try {
			
//			NamedThreadFactory threadFactory = new NamedThreadFactory("LeaderLection");
			for (int i = 0; i < THREAD_NUM; i++) {
				completionService.submit(new Callable<Void>() {
					
					@Override
					public Void call() throws Exception {
						try {
							service.leader();
						} catch (Exception e) {
							logger.error("{}", e);
						}
						return null;
					}
				});
			}
			for (int i = 0; i < THREAD_NUM; i++) {
				completionService.take().get();
			}
		} finally {
			executorService.shutdownNow();
		}
		
	}
}
