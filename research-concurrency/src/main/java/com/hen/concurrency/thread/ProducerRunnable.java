package com.hen.concurrency.thread;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liaohenry
 * @version 2016年8月4日 下午2:55:26
 */
public class ProducerRunnable implements Runnable {

	private Logger logger = LoggerFactory.getLogger(ProducerRunnable.class);
	private Queue<Integer> queue;
	private AtomicBoolean stop = new AtomicBoolean(false);
	private int maxSize;
	private int num;

	public void stop() {
		stop.set(true);
	}
	
	public ProducerRunnable(Queue<Integer> queue, int maxSize, int num) {
		this.queue = queue;
		this.maxSize = maxSize;
		this.num = num;
	}

	@Override
	public void run() {
		Thread.currentThread().setName("PRODUCER "+ this.num);
		while (!this.stop.get()) {
			int i = new Random().nextInt(100);
			try {
				synchronized (queue) {
					while (queue.size() == this.maxSize) {
						logger.info("Queue is full, wait");
						queue.wait();
					}
					queue.add(i);
					logger.info("Push queue: {}, size: {}", i, queue.size());
					queue.notifyAll();
					Thread.sleep(500);
				}
			} catch (InterruptedException e) {
				logger.error("Push queue exception: {}", e);
			}
		}
		logger.info("stop the thread");
	}

}
