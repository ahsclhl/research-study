package com.hen.concurrency.test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* @author  liaohenry
* @version 2016年12月7日 下午7:07:38
*/
public class CacheWithMap {
	private static Logger logger = LoggerFactory.getLogger(CacheWithMap.class);
	private Map<String, String> cache = new ConcurrentHashMap<>();
	private ConcurrentMap<String, String> cacheConcurrent = new ConcurrentHashMap<>();
	
	public void testCache() {
		// need to lock
		cache.put("test", "test");
		
		cacheConcurrent.putIfAbsent("test", "test");
	}
}
