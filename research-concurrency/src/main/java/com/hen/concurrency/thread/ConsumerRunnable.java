package com.hen.concurrency.thread;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liaohenry
 * @version 2016年8月4日 下午3:09:30
 */
public class ConsumerRunnable implements Runnable {
	private Logger logger = LoggerFactory.getLogger(ConsumerRunnable.class);
	private Queue<Integer> queue;
	private AtomicBoolean stop = new AtomicBoolean(false);
	private int num;
	public void stop() {
		stop.set(true);
	}
	public ConsumerRunnable(Queue<Integer> queue, int num) {
		this.queue = queue;
		this.num = num;
	}

	@Override
	public void run() {
		Thread.currentThread().setName("CONSUMER "+ this.num);
		try {
			while (!this.stop.get()) {
				synchronized (queue) {
					while (queue.isEmpty()) {
						logger.info("Queue is empty, wait");
						queue.wait();
					}
					Integer i = queue.remove();
					logger.info("Consume: {}, size: {}", i, queue.size());
					queue.notifyAll();
					Thread.sleep(800);
				}
			}

		} catch (InterruptedException e) {
			logger.error("Consumer exception: {}", e);
		}
		logger.info("stop the thread");
	}

}
