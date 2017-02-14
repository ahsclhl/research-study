package com.hen.practice.alg;

import java.util.Deque;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;

/** 
* @author  liaohenry
* @version 2016年12月29日 上午11:23:28
*/
public class BST {
	private static final Logger logger = LoggerFactory.getLogger(BST.class);
	
	class Node {
		int val;
		Node left;
		Node right;
		
		public Node(int val) {
			this.val = val;
		}
	}
	
	private Node root;
	
	@Before
	public void before() {
		root = insert(null, 15);
		insert(root, 17);
		insert(root, 13);
		insert(root, 8);
		insert(root, 14);
	}
	
	@Test
	public void testView() {
		logger.info("middle view is: ");
		midView(root);
		logger.info("layer view is: ");
		layerView(root);
	}
	
	@Test
	public void testLength() {
		int depth = depth(root);
		logger.info("depth is {}", depth);
		TestCase.assertEquals(depth, 3);
	}
	
	@Test
	public void testNumber() {
		int number = number(root);
		logger.info("number is {}", number);
		TestCase.assertEquals(number, 5);
	}
	
	@Test
	public void testNumberInLayer() {
		int numberIn3 = number(root, 3);
		logger.info("number in 3 layer is {}", numberIn3);
		TestCase.assertEquals(numberIn3, 2);
		
		int numberIn2 = number(root, 2);
		logger.info("number in 2 layer is {}", numberIn2);
		TestCase.assertEquals(numberIn2, 2);
	}
	
	@Test
	public void testEquals() {
		Node root2 = insert(null, 15);
		insert(root2, 13);
		insert(root2, 17);
		insert(root2, 8);
		insert(root2, 14);
		
		Node root3 = insert(null, 15);
		insert(root3, 13);
		insert(root3, 17);
		insert(root3, 8);
		logger.info("layerView about root is:");
		layerView(root);
		logger.info("layerView about root2 is:");
		layerView(root2);
		logger.info("layerView about root3 is:");
		layerView(root3);
		boolean equal = equals(root, root2);
		logger.info("root & root2 are equals: {}", equal);
		TestCase.assertEquals(equal, true);
		equal = equals(root, root3);
		logger.info("root & root3 are equals: {}", equal);
		TestCase.assertEquals(equal, false);
	}
	
	@Test
	public void testFullContain() {
		Node subTree = insert(null, 13);
		insert(subTree, 8);
		insert(subTree, 14);
		logger.info("layerView about subTree is:");
		boolean contain = fullContain(root, subTree);
		logger.info("root contain subTree is: {}", contain);
		TestCase.assertEquals(contain, true);
		
		Node subTree2 = insert(null, 13);
		insert(subTree2, 7);
		logger.info("layerView about subTree2 is:");
		contain = fullContain(root, subTree2);
		logger.info("root contain subTree2 is: {}", contain);
		TestCase.assertEquals(contain, false);
	}
	
	
	public Node insert(Node root, int val) {
		if (root == null) {
			root = new Node(val);
		}
		
		if (val < root.val) {
			root.left = insert(root.left, val);
		}
		if (val > root.val) {
			root.right = insert(root.right, val);
		} 

		return root;
	}
	
	
	public void midView(Node root) {
		if (root.left != null) 
			midView(root.left);
		logger.info("{}", root.val);
		if (root.right != null) 
			midView(root.right);
	}
	
	public void layerView(Node root) {
		Deque<Node> queue = new LinkedList<>();
		queue.add(root);
		Node temp;
		while (queue.size() > 0) {
			temp = queue.poll();
			logger.info("{}", temp.val);
			if (temp.left != null) {
				queue.add(temp.left);
			}
			if (temp.right != null) {
				queue.add(temp .right);
			}
		}
		
	}
	
	public int depth(Node root) {
		if (root == null) {
			return 0;
		}
		int l = depth(root.left);
		int r = depth(root.right);
		return (l > r ? l:r) + 1;
		
	}
	
	public int number(Node root) {
		if (root == null) {
			return 0;
		}
		return number(root.left) + number(root.right) + 1;
	}
	
	public int number(Node root, int k) {
		if (root == null || k < 1) {
			return 0;
		}
		if (k == 1) {
			return 1;
		}
		
		return number(root.left, k - 1) + number(root.right, k - 1);
		
	}
	
	public boolean equals(Node src, Node tgt) {
		if (src == null && tgt == null) {
			return true;
		} 
		
		if (src !=null && tgt != null && src.val == tgt.val) {
			return equals(src.left, tgt.left) && equals(src.right, tgt.right);
		} else {
			return false;
		}
		
	}
	
	public boolean fullContain(Node src, Node tgt) {
		if (src == null || tgt == null) {
			return false;
		}
		
		if (equals(src, tgt)) {
			return true;
		}
		
		// 下面两种写法，当前这种更优化
		if(fullContain(src.left, tgt) || fullContain(src.right, tgt)) {
			return true;
		} else {
			return false;
		}
		
		/* 
		boolean l = fullContain(src.left, tgt);
		boolean r = fullContain(src.right, tgt);
		return l || r;
		*/
	}
	
}
