package com.hen.corejava.test;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* @author  liaohenry
* @version 2016年12月13日 下午4:41:59
*/
public class GenericTest {
	private static final Logger logger = LoggerFactory.getLogger(GenericTest.class);
	
	public void test() {
		
		List<? extends Season> seasons = new ArrayList<>();
		seasons.add(new Spring());
		
		List<? extends Season> seasons2 = new ArrayList<Season>();
		seasons2.add(new Season());
		
		List<? super Spring> springs = new ArrayList<Season>();
//		springs.add(new Spring());
		springs.add(new Season());
		
	}
}
