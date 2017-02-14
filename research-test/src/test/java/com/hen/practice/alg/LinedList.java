package com.hen.practice.alg;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;

/**
 * @author liaohenry
 * @version 2016年12月29日 上午11:17:40 
 * 反转 
 * 是否有环、环的长度、环的起始节点、有环链表的长度 
 * 是否相交
 */
public class LinedList {
	private static final Logger logger = LoggerFactory.getLogger(LinedList.class);

	class Node {
		int val;
		Node next;

		public Node(int val) {
			this.val = val;
			this.next = null;
		}
	}

	private Node root;

	@Before
	public void before() {
		// insert and view all lists
		root = insert(null, 1);
		insert(root, 2);
		insert(root, 3);
		insert(root, 4);
		insert(root, 5);
		insert(root, 6);

		view(root);
	}
	
	@Test
	public void testRevert() {
		Node list1 = insert(null, 1);
		Node revList1 = revert(list1);
		TestCase.assertEquals(list1.val, revList1.val);
		view(revList1);
		
		Node list2 = insert(null, 1);
		insert(list2, 2);
		Node revList2 = revert(list2);
		TestCase.assertEquals(2, revList2.val);
		view(revList2);
		
		Node list3 = insert(null, 1);
		insert(list3, 2);
		insert(list3, 3);
		Node revList3 = revert(list3);
		TestCase.assertEquals(3, revList3.val);
		view(revList3);
		
		Node list4 = insert(null, 1);
		insert(list4, 2);
		insert(list4, 3);
		insert(list4, 4);
		Node revList4 = revert(list4);
		TestCase.assertEquals(4, revList4.val);
		view(revList4);
	}

	public void testRecycle() {
		// if it is recycle
		Node root1 = new Node(1);
		Node node2 = insert(root1, new Node(2));
		insert(root1, new Node(3));
		insert(root1, new Node(4));
		insert(root1, node2);
		// view(root1);
		logger.info("root is recycle: {}", isRec(root));
		logger.info("root1 is recycle: {}", isRec(root1));
	}

	/*
	 * 输入：根节点 root，新节点value val 输出：根节点
	 */
	public Node insert(Node root, int val) {
		if (root == null) {
			root = new Node(val);
			return root;
		}
		Node temp = root;
		while (root.next != null) {
			root = root.next;
		}
		root.next = new Node(val);
		return temp;
	}

	/*
	 * 输入：根节点 root，新节点：newNode 输出：尾节点
	 */
	public Node insert(Node root, Node newNode) {
		if (root == null) {
			root = newNode;
		}
		while (root.next != null) {
			root = root.next;
		}
		root.next = newNode;
		return newNode;
	}

	public void view(Node root) {
		while (root != null) {
			log(root);
			root = root.next;
		}
	}

	public void log(Node root) {
		logger.info("{}", root.val);
	}
	
	public Node revert(Node root) {
		if (root == null || root.next == null) {
			return root;
		}
		Node p = root, q = root.next, t = q.next;
		p.next = null;
		
		while (true) {
			q.next = p;
			if (t == null) {
				return q;
			}
			p = q;
			q = t;
			t = t.next;
		}
	}

	// 错误写法：q.next.next NPE 威胁
	/*
	public boolean isRecycle(Node root) {
		Node p, q;
		p = root;
		q = root.next;

		while (p != null && q != null) {
			if (p == q) {
				return true;
			}
			p = p.next;
			q = q.next.next;
		}

		return false;
	}
	*/

	public boolean isRec(Node root) {
		if (root == null || root.next == null) {
			return false;
		}
		Node p = root, q = root.next;

		while (true) {
			if (p == q) {
				return true;
			}
			if (q.next == null || q.next.next == null) {
				break;
			}
			p = p.next;
			q = q.next.next;
		}

		return false;
	}

	public boolean isCross(Node root1, Node root2) {
		
		return false;
	}
}
