package com.hen.concurrency.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hen.concurrency.threadpool.MyExecutorService;

/** 
* @author  liaohenry
* @version 2016年8月16日 下午5:27:08
* 了解 Executors 的 invokeAny，invoiceAll
*/
public class InvokeService {
	private static final Logger logger = LoggerFactory.getLogger(InvokeService.class);
	@Autowired
	private MyExecutorService executorService;
	
	public void mapReduce() {
//		executorService.getExecutorService().invokeAll(asList)
	}
}
