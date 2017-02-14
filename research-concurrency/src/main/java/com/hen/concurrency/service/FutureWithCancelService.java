package com.hen.concurrency.service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hen.utils.NamedThreadFactory;

/** 
* @author  liaohenry
* @version 2016年8月16日 下午6:35:11
* 基于Future 框架实现cancel 当前正在run 的thread
* 阅读FutureTask.cancel() 的源码发现，future.cancel 
* 只有在 mayInterruptIfRunning = true 时，调用Runnable.interrupt(),然后再去更新FutureTask 的状态
* 		mayInterruptIfRunning = false 时，不会调用 interrupt，只会更新状态，然后返回false 。。。
* 具体的可打断的资源：
* sleep是可以被Future.cacel()中断的
* 线程中的IO阻塞时，线程无法被Future.cancel()中断
* 线程中的synchronized锁阻塞时，线程无法被Future.cancel()方法中断（Lock是可以被中断的）
*/
@Service
public class FutureWithCancelService {
	private static final Logger logger = LoggerFactory.getLogger(FutureWithCancelService.class);
	
	public Future<Void> service() {
		ExecutorService executorService = Executors.newSingleThreadExecutor(new NamedThreadFactory("FUTURE SERVICE"));
		Future<Void> future = executorService.submit(new Callable<Void>() {
						
			@Override
			public Void call() {
				int i = 0;
				while(true) {
					logger.info("running " + i++);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						logger.error("{}", e);
					}
				}
			}
		});
		return future;
	}
}
