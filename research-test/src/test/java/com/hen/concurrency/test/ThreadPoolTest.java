package com.hen.concurrency.test;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.hen.utils.NamedThreadFactory;

/** 
* @author  liaohenry
* @version 2016年12月16日 上午10:56:53
* 
* 
* ThreadPoolExecutor 的参数定义：
* handler -- RejectedExecutionHandler：
* ThreadPoolExecutor.AbortPolicy,  --  A handler for rejected tasks that throws a
* ThreadPoolExecutor.CallerRunsPolicy, --  A handler for rejected tasks that runs the rejected task
     * directly in the calling thread of the {@code execute} method,
     * unless the executor has been shut down, in which case the task
     * is discarded.
* ThreadPoolExecutor.DiscardOldestPolicy, -- * A handler for rejected tasks that discards the oldest unhandled
     * request and then retries {@code execute}, unless the executor
     * is shut down, in which case the task is discarded.
* ThreadPoolExecutor.DiscardPolicy --  * A handler for rejected tasks that silently discards the rejected task, 
* 	with do nothing in code
* 
* --------------------------------------------------
* newSingleThreadExecutor : corePoolSize = 1, maximumPoolSize = 1, keepAliveTime = 0（这个字段显然也没有意义了）
* newFixedThreadPool : corePoolSize = num, maximumPoolSize = num, keepAliveTime = 0（这个字段显然也没有意义了）
* newCachedThreadPool : corePoolSize = 0, maximumPoolSize = MAX_VALUE, keepAliveTime = 60s
* 	默认线程数量为0，所有线程的存活时间为60s
* 
* --------------------------------------------------
* 
* * @param corePoolSize the number of threads to keep in the pool, even
     *        if they are idle, unless {@code allowCoreThreadTimeOut} is set
     * @param maximumPoolSize the maximum number of threads to allow in the
     *        pool
     * @param keepAliveTime when the number of threads is greater than
     *        the core, this is the maximum time that excess idle threads
     *        will wait for new tasks before terminating.
     * @param unit the time unit for the {@code keepAliveTime} argument
     * @param workQueue the queue to use for holding tasks before they are
     *        executed.  This queue will hold only the {@code Runnable}
     *        tasks submitted by the {@code execute} method.
     * @param threadFactory the factory to use when the executor
     *        creates a new thread
     * @param handler the handler to use when execution is blocked
     *        because the thread bounds and queue capacities are reached
     *        
*/
public class ThreadPoolTest {
	
	ExecutorService singleService = Executors.newSingleThreadExecutor();
	ExecutorService fixService = Executors.newFixedThreadPool(10);
	ExecutorService cacheService = Executors.newCachedThreadPool();
	
	/*
	public ThreadPoolExecutor(int corePoolSize,
            int maximumPoolSize,
            long keepAliveTime,
            TimeUnit unit,
            BlockingQueue<Runnable> workQueue,
            ThreadFactory threadFactory,
            RejectedExecutionHandler handler) 
            */
	
	ExecutorService customizedService = new ThreadPoolExecutor(10, 500, 10, TimeUnit.SECONDS, 
			new ArrayBlockingQueue<Runnable>(1000), new NamedThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy() );
	BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);
}
