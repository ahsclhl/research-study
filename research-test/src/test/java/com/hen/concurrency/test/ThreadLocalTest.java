package com.hen.concurrency.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hen.concurrency.bean.BaseVal;
import com.hen.concurrency.thread.ReadThreadLocalService;
import com.hen.concurrency.thread.WriteThreadLocalService;

/** 
* @author  liaohenry
* @version 2016年8月11日 下午5:26:29
* 首先，ThreadLocal 的功能：ThreadLocal 并不会为每个变量copy 一份；
* 因为并没有new 一个新的对象出来，ThreadLocal 只是一个Map<Thread, Value> 其中value 是WeakReference，所以只是对set 的Object 的引用
* 因此在dataSource 的使用中，所有的Thread 都是看到同一个dataSource
* 
* ThreadLocal 是Local的：
* 但是又有一个说法是：每个ThreadLocal 的变量是local 的，也好理解
* 比如：在用户id 存入 servletContext 的例子中，每个ThreadLoca 的Map的value set 的用户 id 是不同的
* 每个thread 会维护一个ThreadLocalMap： Thread t = Thread.currentThread(); ThreadLocalMap map = getMap(t);
* 
* ThreadLocal 是GLobal的：
* ThreadLocal的Map key 是thread，因此在thread 的生命空间中，都是可以拿到value的
* 通常使用static ThreadLocal 变量，作为global 变量使用
* 在servlet 的开头，塞入user id，在service 和 dao 层中，继续获取static ThreadLocal.get() 即可获取当前线程对应的value
* 该方法，更优于函数传参的方式
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/common-context.xml",
		"classpath:spring/concurrency-context.xml"
})
public class ThreadLocalTest {
	
	@Test
	public void test() throws InterruptedException {
		BaseVal baseVal = new BaseVal();
		baseVal.setVal("old val");
		
		WriteThreadLocalService writeService = new WriteThreadLocalService(baseVal);
		ReadThreadLocalService readService = new ReadThreadLocalService(baseVal);
		
		Thread thread1 = new Thread(writeService);
		thread1.start();
		Thread.sleep(1000);
		
		Thread thread2 = new Thread(readService);
		thread2.start();
		
		// wait the ending of all threads
		thread1.join();
		thread2.join();
		
	}
}
