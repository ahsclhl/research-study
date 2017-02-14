package com.hen.concurrency.service;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hen.concurrency.thread.CalWorker;
import com.hen.utils.NamedThreadFactory;

/**
 * @author liaohenry
 * @version 2016年8月16日 下午6:34:47 基于completionService实现Batch 操作
 * ExecutorCompletionService 和 ExecutorService 都可以拿到线程的结果 -- Future.get() 
 * 他们的区别是：
 * ExecutorService 需要按照一定顺序去等待线程的结果，Future.get() 是阻塞操作，可能某个线程已经完成了，但是被阻塞了，没有及时返回
 * ExecutorCompletionService 是先返回已经完成的线程的结果
 */
@Component
public class BatchCompletionService {
	private static final Logger logger = LoggerFactory.getLogger(BatchCompletionService.class);

	public void completeService() {
		int cnt = 4;
		CompletionService<Integer> service = new ExecutorCompletionService<>(
				Executors.newFixedThreadPool(cnt, new NamedThreadFactory()));
		for (int i = 0; i < cnt; i++) {
			service.submit(new CalWorker(i));
		}

		try {
			for (int i = 0; i < cnt; i++) {
				Future<Integer> future;
				future = service.take();
				Integer val = future.get();
				logger.info("cal res is :{}", val);
			}
		} catch (InterruptedException | ExecutionException e) {
			logger.error("=== excepiton: {}", e);
		}
	}

	public void waitGet() {

	}
}
