package com.hen.javacore.test;
/** 
* @author  liaohenry
* @version 2016年12月6日 上午9:30:06
* 懒加载单例模式，基于static class
* 利用static 变量只加载一次
* static 内部类只有在
*/
public class SingletonLazy {
	
	private static class InstanceHolder {
		public static SingletonLazy instance = new SingletonLazy();
	}
	
	public SingletonLazy instance() {
		synchronized (this) {
			
		}
		return InstanceHolder.instance;
	}
	
	// 私有化构造函数，是外部不能实例化class
	private SingletonLazy() { }
 }
