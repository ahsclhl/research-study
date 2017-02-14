package com.hen.practice.alg;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liaohenry
 * @version 2016年12月19日 下午4:25:35 
 * 堆使用数组存储：i 的子节点 -- a[2*i], a[2*i+1] 堆的操作：
 * 重建：从左右节点中查找最小值 -> 和父节点比较 -> 
 * if 较小则 小数往上 a[i] = a[j] 大数往下 i = j ...  a[i] = a[j],if not 则停止，说明：下面的堆已经满足最小堆 ->
 *      重复向上，以使每棵树都是最小/大堆 
 * 堆化数组：从 n/2-1 节点，自下往上重建堆 
 * 堆排序：第0个数据是最小的 -- swap(a[0], a[i]) -> rebuild heap -> again ...
 * 
 */
public class HeapSort {
	private static final Logger logger = LoggerFactory.getLogger(HeapSort.class);
	
	public void minHeapFixdown(int a[], int i, int n) {
		int j, temp;

		temp = a[i];
		j = 2 * i + 1;
		while (j < n) {
			if (j + 1 < n && a[j + 1] < a[j]) // 在左右孩子中找最小的
				j++;

			if (a[j] >= temp)
				break;

			a[i] = a[j]; // 把较小的子结点往上移动,替换它的父结点
			i = j;
			j = 2 * i + 1;
			
		}
		a[i] = temp;
	}
	
	public void minHeapDown(int a[], int i, int n) {
		int temp = a[i], j = 2*i +1;
		while (j<n) {
			if (j+1<n && a[j+1] < a[j]) {
				j++;
			}
			
			if(temp <= a[j])
				break;
			
			a[i] = a[j];
			i=j;
			j=2*i + 1;
		}
		a[i] = temp;
	}

	// 建立最小堆
	public void makeMinHeap(int a[], int n) {
		for (int i = n/2 - 1; i>=0; i--) {
			minHeapDown(a, i, n);
			logger.info("min heap: {}", log(a));
		}
		logger.info("min heap: {}", log(a));
	}
	
//	@Test
	public void testMakeMinHeap() {
		int a[] = {99,36,66,45,94,23,54,55,19,12};
		makeMinHeap(a, 10);
	}
	
	@Test
	public void testSortMinHeap() {
		int a[] = {45,12,66,19,94,23,54,36,99,55};
		int temp;
		for (int i=a.length; i>0; i--) {
			makeMinHeap(a, i);
			
			temp = a[0];
			a[0] = a[i-1];
			a[i-1] = temp;
			logger.info("after sort: {}", log(a));
		}
		logger.info("after sort: {}", log(a));
	}
	
	public static String log(int[] a) {
		StringBuilder builder = new StringBuilder();
		for (int t : a) {
			builder.append(t).append(",");
		}
		return builder.toString();
	}
	
}
