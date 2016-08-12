package com.hen.mq.producer;

import java.util.UUID;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.transaction.annotation.Transactional;

/** 
* @author  liaohenry
* @version 2016年8月12日 下午3:26:42
*/
public class TopicProducer {
	private static final Logger logger = LoggerFactory.getLogger(TopicProducer.class);

	private JmsTemplate jmsTemplate;

	// Not working with annotation -- @Transactional
	@Transactional
	public void publish(final String msg) {
		Destination destination = jmsTemplate.getDefaultDestination();
		logger.info("Producer.sendMessage -- destination: {}, message: {}", destination, msg);
		jmsTemplate.send(new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				Message message = session.createTextMessage(msg);
				logger.info("createMessage: {}", message);
				return message;
			}
		});
	}

	public void publish(boolean commit) throws JMSException {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
		Connection connection = factory.createConnection();
		connection.start();

		Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		Topic topic = session.createTopic("henry");
		MessageProducer producer = session.createProducer(topic);
		Message message = session.createTextMessage("Message from henry: " + UUID.randomUUID().toString());
		logger.info("message: {}", message);
		producer.send(message);
		if (commit) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();
		logger.info("end");
	}

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

}
