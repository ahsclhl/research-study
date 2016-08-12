package com.hen.utils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/** 
* @author  liaohenry
* @version 2016年8月11日 下午1:57:48
*/
public class NamedThreadFactory implements ThreadFactory {
	private final AtomicInteger threadNum = new AtomicInteger(1);
	private String prefix;
	
	public NamedThreadFactory() {
		
	}
	
	public NamedThreadFactory(String name) {
		this.prefix = generateName(name);
	}
	
	@Override
	public Thread newThread(final Runnable r) {
		final Thread thread = new Thread(r);
		thread.setName(prefix + threadNum.getAndIncrement());
		return thread;
	}
	
	private String generateName(String name) {
		return name + "-thread-";
	}

}
