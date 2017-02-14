package com.hen.corejava.test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.collections.ArrayStack;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* @author  liaohenry
* @version 2016年12月12日 下午10:08:12
* -----------------------------------------
* 同步：Vector HashTable ConcurrentTable
* 同步的实现方式也很简单，对加入元素方法同步
* 
* fail-fast:
* 对于不同步的数据结构并发操作时，内部维护modCount，每次加入数据是+1，遍历过程中check modCount是否变化，变化则抛出exception。
* impossible to make a hard guarantee in the presence of unsynchronized concurrent modification:
* 1. race condition in rehash: caused by place the new mapping in the head of linked list of bucket
* 2. race condition in put: overlap modification from other thread
* 
* tail traversing -- new added object need to traverse to the end of linked list if the new object are placed into the end of list.
* 
* -----------------------------------------
* 
* 顺序有两种：迭代顺序，即插入、访问顺序；基于 Comparable 排序。
* 
* -----------------------------------------
* Map：HashMap HashTable TreeMap LinkedHashMap
* 无序：HashMap HashTable
* 有序：TreeMap LinkedHashMap
* HashMap 的实现是一个数组 + 链表，基本元素是：Entry<K,V>
* 根据hash() 算出hashCode，根据indexOf()算出数组的位置；
* 如果该位置没有数据，则塞入数据；如果该位置有数据，则根据equals()查找链表，如果能找到则替代，否则放到链表的 --- “顶端”。
* 因此重写equals 必须重写 hashCode 方法，两者的关系是：
* 	1、如果两个对象相等，那么他们一定有相同的哈希值（hash code）。
* 	2、如果两个对象的哈希值相等，那么这两个对象有可能相等也有可能不相等。（需要再通过equals来判断）
* 
* LinkedHashMap 是Entry 包含before、after元素，从而实现双向链表。记录插入的顺序。
* 
* TreeMap 基于红黑树，排序。因此插入最慢，O(logn)
* -----------------------------------------
* Set: HashSet LinkedHashSet TreeSet
* Set 都是基于Map实现的，其中定义一个Map 和 一个内部变量作为Map 的value
* 
* HashSet 基于 HashMap，LinkedHashSet 基于LinkedHashMap，TreeSet 基于 TreeMap
* 
* -----------------------------------------
* List：ArrayList LinkedList Vector Stack Queue
* 和数组区别：动态扩容
* ArrayList 和 Vector 设计基本相同，只是Vector 是线程安全的
* LinkedList 是基于双向链表的设计，因此相比ArrayList，需要调整数组内容的操作：add remove 更快速；get set 操作要慢一点
* 
* !!LinkedList 还实现了 Deque 接口，因此多出了 offer peek poll 方法
* 
* CopyOnWriteList
* 基于snapShot 的方式 preclude interference
* mutation -- add remove set 都是 lock的，因此不存在多线程的并发mutation
* traversal -- 指向当前的array，因此是当前正在看不到write 的数据
* -----------------------------------------
* Map 的size() 接口：返回键值对的数量
* HashMap 内部维护size 变量，在addEntry 是自增；
* ConcurrentHashMap：
* 首先，不加锁方式对所有的 Segment 统计size，通过modCount 判断是否有并发修改；
* 如果，不加锁方式失败，则对所有 Segment 加锁，再统计count
* 
* -----------------------------------------
* 
* Stack 实现了堆栈，继承与Vector
* 
* ----------------------------------------
* 
* Comparable: class 具有比较的能力
* Comparator: 自定义的比较方法，策略模式
*/
public class CollectionTest {
	private static final Logger logger = LoggerFactory.getLogger(CollectionTest.class);
	
//	@Test
	public void testCopyOnWrite() {
		List<Integer> concurrentList = new CopyOnWriteArrayList<>();
		concurrentList.add(10);
		
		List<Integer> arrayList = new ArrayList<>();
		arrayList.add(20);
		
		Iterator<Integer> iterator = concurrentList.iterator();
		while (iterator.hasNext()) {
			logger.info(iterator.next().toString());
		}
	}
	
//	@Test
	public void testMap() {
		Map<Integer, Integer> hash = new HashMap<Integer, Integer>();
		hash.put(1, 10);
		hash.put(1, 20);
		
		Map<Integer, Integer> hashTable = new Hashtable<>();
		hashTable.put(1, 10);
		hashTable.put(2, 20);
		
		Map<Integer, Integer> linkHash = new LinkedHashMap<>();
		linkHash.put(1, 10);
		linkHash.put(2, 20);
		
		linkHash.size();
		
		Map<Integer, Integer> treeMap = new TreeMap<>();
		treeMap.put(1, 10);
		
		Map<Integer, Integer> conMap = new ConcurrentHashMap<>();
		conMap.put(1, 10);
		conMap.get(1);
		
		Iterator<Integer> iterator1 = conMap.keySet().iterator();
		while (iterator1.hasNext()) {
			Integer key = iterator1.next();
			Integer val = conMap.get(key);
		}
		
		conMap.size();
		
		Iterator<Map.Entry<Integer,Integer>> iterator = conMap.entrySet().iterator();
		iterator.next();
		
		// vector & list
		
		List<Integer> vec = new Vector<>();
		vec.add(10);
		
		List<Integer> list = new ArrayList<Integer>();
		list.add(10);
		
		Queue<Integer> queue = new ArrayBlockingQueue<>(10);
		
		// set
		// private transient NavigableMap<E,Object> m;
		Set<Integer> treeSet = new TreeSet<>();
		treeSet.add(10);
		
	}
	
	public void testTraverse() {
		Map<Integer, Integer> linkHash = new LinkedHashMap<>();
		linkHash.put(1, 10);
		linkHash.put(2, 20);
		
		for (Integer key : linkHash.keySet()) {
			Integer val = linkHash.get(key);
			logger.info("{} : {}", key, val);
		}
		
//		for (Map.Entry<Integer, Integer> key : linkHash.entrySet()) {
//			Integer key = key.
//			logger.info("{} : {}", key, val);
//		}
		
	}
	
	class ReadRunable implements Runnable {
		private Map<Integer, Integer> hashMap;
		
		public ReadRunable (Map<Integer, Integer> hashMap) {
			this.hashMap = hashMap;
		}
		
		@Override
		public void run() {
			logger.info("read: {}", hashMap.get(1));
		}
		
	}
	
	class WriteRunable implements Runnable {
		private Map<Integer, Integer> hashMap;
		
		public WriteRunable (Map<Integer, Integer> hashMap) {
			this.hashMap = hashMap;
		}
		
		@Override
		public void run() {
			hashMap.remove(1);
			logger.info("read: {}", hashMap.get(1));
		}
		
	}
	
//	@Test
	public void testRaceConditionForMap() {
		Map<Integer, Integer> hashMap = new ConcurrentHashMap<>();
		hashMap.put(1, 10);
		Thread readThread = new Thread(new ReadRunable(hashMap));
		Thread writeThread = new Thread(new WriteRunable(hashMap));
		
		try {
			readThread.start();
			Thread.sleep(1000);
			writeThread.start();
			
			readThread.join();
			writeThread.join();
		} catch (InterruptedException e) {
			logger.error("{}", e);
		}
	}
	
	public void testStack() {
		Stack<Integer> stack = new Stack<>();
		
		stack.push(1);
		stack.push(2);
		logger.info("pop: {}", stack.pop());
		logger.info("pop: {}", stack.pop());
		
		
		
		new ArrayStack();
	}
	
	@Test
	public void testQueue() {
		
		Queue<Integer> queue = new ArrayBlockingQueue<>(10);
		queue.add(1);
		queue.add(2);
		queue.add(3);
		logger.info("pop ArrayBlockingQueue");
		logger.info("{}", queue.poll());
		logger.info("{}", queue.poll());
		logger.info("{}", queue.poll());
		
		Deque<Integer> deque = new ArrayDeque<>();
		logger.info("push ArrayDeque");
		deque.push(1);
		deque.push(2);
		deque.push(3);
		logger.info("pop ArrayDeque");
		logger.info("{}", deque.poll());
		logger.info("{}", deque.poll());
		logger.info("{}", deque.poll());
		
		Deque<Integer> linkList = new LinkedList<>();
		logger.info("push LinkedList");
		linkList.push(1);
		linkList.push(2);
		linkList.push(3);
		logger.info("pop LinkedList");
		logger.info("{}", linkList.pop());
		logger.info("{}", linkList.pop());
		logger.info("{}", linkList.pop());
//		linkList.element();
		
		Stack<Integer> stack = new Stack<>();
		logger.info("push Stack");
		stack.push(10);
		stack.push(20);
		stack.push(30);
		logger.info("pop Stack");
		logger.info("{}", stack.pop());
		logger.info("{}", stack.pop());
		logger.info("{}", stack.pop());
		
		Vector<Integer> vector = new Vector<>();
		
	}
	
	@Test
	public void testTreeMap() {
		
		Map<Integer, String> treeMapDesc = new TreeMap<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer val1, Integer val2) {
				return val2.compareTo(val1);
			}

		});
		
		treeMapDesc.put(1, "A");
		treeMapDesc.put(3, "C");
		treeMapDesc.put(2, "B");
		
		Iterator<Integer> iterator = treeMapDesc.keySet().iterator();
		while (iterator.hasNext()) {
			Integer key = iterator.next();
			String value = treeMapDesc.get(key);
			logger.info("key: {} -- value: {}", key, value);
		}
		
		Map<Integer, String> treeMap = new TreeMap<>();
		treeMap.put(1, "a");
		treeMap.put(3, "c");
		treeMap.put(2, "b");
		
		Iterator<Integer> iterator2 = treeMap.keySet().iterator();
		while (iterator2.hasNext()) {
			Integer key = (Integer) iterator2.next();
			String value = treeMapDesc.get(key);
			logger.info("key: {} -- value: {}", key, value);
		}
		
	}
	
}
