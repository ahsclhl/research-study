package com.hen.practice.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;

/**
 * @author liaohenry
 * @version 2016年12月29日 上午11:18:16
 * 泛型方法：public static <T extends Comparable<T>> List<T> merge(List<T> l, List<T> r) 
 * 
 * 1. merge 已经排好序的两个数组
 */
public class Array {
	private static final Logger logger = LoggerFactory.getLogger(Array.class);

	public static <T extends Comparable<T>> List<T> merge(List<T> l, List<T> r) {
		List<T> res = new ArrayList<>();
		int i = 0, j = 0;
		while (true) {
			if (l.get(i).compareTo(r.get(j)) <= 0) {
				res.add(l.get(i++));
				if (i == l.size()) {
					break;
				}
			} else {
				res.add(r.get(j++));
				if (j == r.size()) {
					break;
				}
			}
		}

		if (i < l.size()) {
			for (; i<l.size();i++) {
				res.add(l.get(i));
			}
		}

		if (j < r.size()) {
			for (; j<r.size();j++) {
				res.add(r.get(j));
			}
		}

		logger.info("left: {}", l);
		logger.info("right: {}", r);
		logger.info("result: {}", res);
		// Collections.binarySearch(list, key)
		return res;
	}
	
	@Test
	public void testMerge() {
		List<Integer> left1 = Arrays.asList(1,3,5,7);
		List<Integer> right1 = Arrays.asList(2,8);
		List<Integer> res1 = merge(left1, right1);
		TestCase.assertEquals(res1, Arrays.asList(1, 2, 3, 5, 7, 8));
		
		List<Integer> left2 = Arrays.asList(1,3,5);
		List<Integer> right2 = Arrays.asList(2,4,6);
		List<Integer> res2 = merge(left2, right2);
		TestCase.assertEquals(res2, Arrays.asList(1, 2, 3, 4, 5, 6));
	}
	
}
