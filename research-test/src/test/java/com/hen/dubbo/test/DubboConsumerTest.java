package com.hen.dubbo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hen.dubbo.api.HelloProvider;

/** 
* @author  liaohenry
* @version 2016年8月12日 下午6:02:11
* 测试dubbo 消费
* consumer-context 中的 dubbo:reference 指向到和 provider-context 中 dubbo:service 一样的 interface
* 这也说明了，我们应该include provider 导出的 api interface，这当然也是合理的
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"classpath:spring/common-context.xml", 
		"classpath:spring/dubbo-consumer-context.xml"
})
public class DubboConsumerTest {
	private static final Logger logger = LoggerFactory.getLogger(DubboProviderTest.class);
	
	@Autowired
	private HelloProvider helloService;
	
	@Test
	public void testHello() {
		String msg = helloService.sayHello();
		logger.info("result from Dubbo service: {}", msg);
	}
}
