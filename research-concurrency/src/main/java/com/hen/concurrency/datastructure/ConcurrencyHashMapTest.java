package com.hen.concurrency.datastructure;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* @author  liaohenry
* @version 2016年11月30日 上午9:07:27
*/
public class ConcurrencyHashMapTest {
	private Logger logger = LoggerFactory.getLogger(ConcurrencyHashMapTest.class);
	private Map<Integer, Integer> gMap = new ConcurrentHashMap<>();
	
	public void setValue() {
		gMap.put(33, 333);
	}
	
}
