package com.hen.concurrency.service;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hen.concurrency.thread.ConsumerRunnable;
import com.hen.concurrency.thread.ProducerRunnable;

/**
 * @author liaohenry
 * @version 2016年8月4日 下午3:22:40
 * 测试：synchronize, await, notifyAll
 * 如果 Producer Q满了，则await，等待notify；如果Consumer Q已经清空，则await，等待notify
 */
@Service
public class ProduceConsumeService {
	private Logger logger = LoggerFactory.getLogger(ProduceConsumeService.class);

	public void start() {
		Queue<Integer> queue = new LinkedList<>();
		ProducerRunnable producer = new ProducerRunnable(queue, 10, 1);
		ConsumerRunnable consumer1 = new ConsumerRunnable(queue, 1);
		ConsumerRunnable consumer2 = new ConsumerRunnable(queue, 2);

		ExecutorService producerExecutor = Executors.newFixedThreadPool(1);
		producerExecutor.submit(producer);

		try {
			Thread.sleep(1000);
			ExecutorService consumerExecutor = Executors.newFixedThreadPool(1);
			consumerExecutor.submit(consumer1);
//			consumerExecutor.submit(consumer2);

			producerExecutor.shutdown();
			consumerExecutor.shutdown();

			Thread.sleep(10000);
		} catch (InterruptedException e) {
			logger.error("{}", e);
		}

		producer.stop();
		consumer1.stop();
		consumer2.stop();
	}

}
