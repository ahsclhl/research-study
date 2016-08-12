package com.hen.mq.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* @author  liaohenry
* @version 2016年8月12日 下午3:29:42
*/
public class Consumer implements MessageListener {
	private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

	@Override
	public void onMessage(Message msg) {
		TextMessage textMessage = (TextMessage)msg;
		try {
			logger.info("Consumer.receive() message: {}", textMessage.getText());
		} catch (JMSException e) {
			logger.error("{}", e);
		}
		
	}
}
