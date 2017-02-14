package com.hen.practice.datasructure;

import org.junit.Assert;
import org.junit.Test;

/** 
* @author  liaohenry
* @version 2016年12月19日 下午2:19:48
*/
public class StackTest {
	
	@Test
	public void testLinkedStack() {
		LinkedStack<Integer> stack = new  LinkedStack<>();
		stack.push(1);
		stack.push(2);
		
		Assert.assertEquals(new Integer(2), stack.pop());
		Assert.assertEquals(new Integer(1), stack.pop());

	}
}
