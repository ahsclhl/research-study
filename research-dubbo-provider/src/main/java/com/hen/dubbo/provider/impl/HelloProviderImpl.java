package com.hen.dubbo.provider.impl;

/** 
* @author  liaohenry
* @version 2016年8月12日 下午2:36:46
*/
public class HelloProviderImpl implements com.hen.dubbo.api.HelloProvider {

	@Override
	public String sayHello() {
		return "Hello from Henry";
	}

}
