package com.hen.test.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 
* @author  liaohenry
* @version 2016年10月13日 下午6:44:25
*/
@Service
public class ThreadValueService {
	private Logger logger = LoggerFactory.getLogger(ThreadValueService.class);
	// 非常好的sample
	// ThreadLocal 包装的变量，每个thread 会集成，也就是说第一次进来是null，以后的线程进来都是非null的
	// 每个线程包装的变量内容是对立的
	// 并且线程退出时，会自动析构包装的变量，因此下一个thread看到的包装的变量为null
	private ThreadLocal<List<String>> threadItems;
	// 成员变量，会被多个线程分享
	private List<String> items;
	
	public List<String> setThreadItem() {
		try {
			logger.info("start items: {}", threadItems);
			if (threadItems == null) {
				threadItems = new ThreadLocal<>();
			}
			threadItems.set(new ArrayList<String>());
			threadItems.get().add("i1");
		} catch (Exception e) {
			logger.error("error: {}", e);
		}
		return threadItems.get();
	}
	
	public List<String> setItem() {
		try {
			logger.info("start items: {}", items);
			if (items == null) {
				items = new ArrayList<>();
			}
			items.add("i1");
		} catch (Exception e) {
			logger.error("error: {}", e);
		}
		return items;
	}
}
