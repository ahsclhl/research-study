package com.hen.practice.alg;

import java.util.Arrays;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;

/** 
* @author  liaohenry
* @version 2016年12月31日 下午10:09:27
* 堆排序
* 
* 堆使用数组存储：i 的子节点 -- a[2*i], a[2*i+1] 堆的操作：
* 重建：从左右节点中查找最小值 -> 和父节点比较 -> 
* if 较小则 小数往上 a[i] = a[j] 大数往下 i = j ...  a[i] = a[j],if not 则停止，说明：下面的堆已经满足最小堆 ->
*      重复向上，以使每棵树都是最小/大堆 
* 堆化数组：从 n/2-1 节点，自下往上重建堆 
* 堆排序：第0个数据是最小的 -- swap(a[0], a[i]) -> rebuild heap -> again ...
* --------------------
* 快速排序
* 
* 中间值(左右分片) + 挖坑
* 挖出最左边的值作为中间值，右边-- 找到比中间值小的，替换上一个坑 == 左边的值；左边++ 找到比中间值大的，替换上一个坑 == 右边挪走的坑；初始的中间值填坑；
* 对左、右两边分别遍历
* 
* 每一步操作，记得判断左<右
* --------------------
* 插入排序
* 桶排序
* 
* 
*/
public class Sort {
	private static final Logger logger = LoggerFactory.getLogger(Sort.class);
	
	@Test
	public void testSort() {
		int a[] = {45,12,66,19,94,23,54,36,99,55};
		quickSort(a, 0, 9);
		TestCase.assertEquals(Arrays.asList(12,19,23,36,45,54,55,66,94,99), Arrays.asList(a));
	}

	@Test
	public void testHeap() {
		int a[] = {45,12,66,19,94,23,54,36,99,55};
		heapSort(a);
		TestCase.assertEquals(Arrays.asList(99,94,66,55,54,45,36,23,19,12), a);
	}
	
	public void quickSort(int[] a, int start, int end) {
		if (start>=end) {
			return;
		}
		
		int i = start, j = end, temp = a[i];
		while (i<j) {
			while (i<j && a[j] >= temp) {
				j--;
			}
			
			if(i<j)
				a[i++] = a[j];
			
			while (i<j && a[i] <= temp) {
				i++;
			}
			
			if(i<j)
				a[j--] = a[i];
		}
		a[i] = temp;
		logger.info("after sort: {}", log(a));
		quickSort(a, start, i-1);
		quickSort(a, i+1, end);
	}
	
	/* ---------- Heap Sort -----------*/
	public void minHeap(int a[], int i, int n) {
		
		int j = 2*i +1, temp = a[i];
		while (j<n) {
			if(j+1 < n && a[j+1] < a[j])
				j++;
			
			if(temp < a[j])		// important
				break;
			
			a[i] = a[j];
			i=j;
			j=2*i+1;
		}
		a[i] = temp;
	}
	
	public void makeHeap(int a[], int n) {
		for (int i = n/2 - 1; i >= 0; i--) {
			minHeap(a, i, n);
		}
	}
	
	public void heapSort(int a[]) {
		int temp;
		for(int i = a.length; i>0; i--) {
			makeHeap(a, i);
			logger.info("min heap: {}", log(a));
			temp = a[i-1];
			a[i-1] = a[0];
			a[0] = temp;
		}
	}
	
	//------------------------------------//
	public void heapDown2(int a[], int i, int n) {
		int temp = a[i], j = 2*i + 1;
		while(j<n) {
			if(a[j+1] > a[j])
				j++;
			
			if(temp < a[i])
				break;
			
			a[i] = a[j];
			i = j;
			j = 2*i + 1;
		}
		a[i] = temp;
	}
	
	public void makeHeap2(int a[], int n) {
		for(int i = n/2 - 1; i>=0; i--) {
			heapDown2(a, i, n);
		}
	}
	
	public void heapSort2(int a[]) {
		int temp;
		for (int n = a.length; n > 0; n--) {
			makeHeap2(a, n);
			temp = a[0];
			a[0] = a[n-1];
			a[n-1] = temp;
		}
	}
	
	//------------------------------------//
	
	public static String log(int[] a) {
		StringBuilder builder = new StringBuilder();
		for (int t : a) {
			builder.append(t).append(",");
		}
		return builder.toString();
	}
}
