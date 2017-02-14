package com.hen.mq.kafka;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;

/** 
* @author  liaohenry
* @version 2016年9月13日 下午6:38:09
*/
public class KafkaProducer {
	private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
	private String topic;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void producer(String msg) {
		Producer producer = createProducer();
		producer.send(new KeyedMessage<Integer, String>(this.topic, msg));
		logger.info("==>> send msg: {}", msg);
	}
	
	@SuppressWarnings("rawtypes")
	private Producer createProducer() {
		Properties properties = new Properties();
		properties.put("zookeeper.connect", "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183");//声明zk
		properties.put("serializer.class", StringEncoder.class.getName());
		properties.put("metadata.broker.list", "127.0.0.1:9092");// 声明kafka broker
		return new Producer<Integer, String>(new ProducerConfig(properties));
	}
}
