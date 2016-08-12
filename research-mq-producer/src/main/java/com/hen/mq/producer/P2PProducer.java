package com.hen.mq.producer;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * @author liaohenry
 * @version 2016年8月12日 下午3:18:54
 */
public class P2PProducer {
	private static final Logger logger = LoggerFactory.getLogger(P2PProducer.class);

	private JmsTemplate jmsTemplate;

	public void hello() {
		logger.info("hello()");
		Destination defaultDest = jmsTemplate.getDefaultDestination();
		sendMessage(defaultDest, "hello from Henry");
	}

	public void sendMessage(String msg) {
		Destination defaultDest = jmsTemplate.getDefaultDestination();
		sendMessage(defaultDest, msg);
	}

	public void sendMessage(Destination destination, final String msg) {
		logger.info("Producer.sendMessage -- destination: {}, message: {}", destination, msg);
		jmsTemplate.send(destination, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				Message message = session.createTextMessage(msg);
				logger.info("createMessage: {}", message);
				return message;
			}
		});
	}

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
}
