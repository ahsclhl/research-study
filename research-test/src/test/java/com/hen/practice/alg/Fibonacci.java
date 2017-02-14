package com.hen.practice.alg;

import org.junit.Test;

import junit.framework.TestCase;

/** 
* @author  liaohenry
* @version 2017年1月17日 下午4:57:09
*/
public class Fibonacci {
	
	public static int cal(int i) {
		Integer.valueOf("1111");
		if(i <2 ) 
			return 1;
		return cal(i-1) + cal(i-2);
	}
	
	@Test
	public void testCal() {
		TestCase.assertEquals(1, cal(0));
		TestCase.assertEquals(1, cal(1));
		TestCase.assertEquals(2, cal(2));
		TestCase.assertEquals(3, cal(3));
		TestCase.assertEquals(5, cal(4));
	}
}
