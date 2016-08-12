package com.hen.mq.test;

import javax.jms.JMSException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hen.mq.producer.P2PProducer;
import com.hen.mq.producer.TopicProducer;

/** 
* @author  liaohenry
* @version 2016年8月12日 下午5:19:50
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"classpath:spring/common-context.xml", 
		"classpath:spring/mq-producer-context.xml",
		"classpath:spring/mq-consumer-context.xml" 
})
public class ProducerConsumerTest {
	
	private static final Logger logger = LoggerFactory.getLogger(ProducerConsumerTest.class);
	
	@Autowired
	private P2PProducer p2pProducer;
	@Autowired
	private TopicProducer topicProducer;
	
	private boolean commitMsg;
	
	@Before
	public void setCommitMsg() {
		this.commitMsg = true;
	}
	
	@Test
	public void testP2PProducer() {
		p2pProducer.sendMessage("Hello ~~~");
	}
	
	@Test
	public void testPubSubProducer() {
		topicProducer.publish("hello all ~~~");
	}
	
	@Test
	public void testPubSubProducerWithTransaction() {
		try {
			topicProducer.publish(this.commitMsg);
		} catch (JMSException e) {
			logger.error("{}", e);
		}
	}
}
