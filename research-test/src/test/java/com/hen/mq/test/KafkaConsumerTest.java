package com.hen.mq.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hen.mq.kafka.KafkaConsumer;

/** 
* @author  liaohenry
* @version 2016年9月19日 下午2:05:59
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"classpath:spring/common-context.xml" 
})
public class KafkaConsumerTest {
	
	@Test
	public void testConsumer() {
		KafkaConsumer consumer = new KafkaConsumer("hen6", 1, "group1");
		consumer.init();
	}
}
