package com.hen.practice.datasructure;
/** 
* @author  liaohenry
* @version 2016年12月19日 下午2:14:48
* 基于链表实现 stack
*/
public class LinkedStack<T> {
	class Node {
		T val;
		Node next;
		
		public Node(T val) {
			this.val = val;
		}
	}
	
	private Node root;
	
	public void push(T val) {
		Node node = new Node(val);
		node.next = root;
		root = node;
	}
	
	public T pop() {
		if (root == null) {
			return null;
		}
		T val = root.val;
		root = root.next;
		return val;
	}
	
}
