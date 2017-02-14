package com.hen.corejava.test;
/** 
* @author  liaohenry
* @version 2016年12月13日 下午5:06:18
* String 是静态的：private final char value[];
* StringBuffer 和 StringBuilder 的区别就是，前者是支持同步的
*/
public class StringTest {

	public void test() {
		// private final char value[];
		String str = new String("123");
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("123");
		
		StringBuilder builder = new StringBuilder();
		buffer.append("456");
	}
}
