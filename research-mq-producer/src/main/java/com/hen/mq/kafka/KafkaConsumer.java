package com.hen.mq.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;

/**
 * @author liaohenry
 * @version 2016年9月19日 下午1:49:21
 */
public class KafkaConsumer {
	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

	private String topic;
	private int partionNum = 1;
	private String group;
	private ExecutorService threadPool;

	public KafkaConsumer(String topic, int partionNum, String group) {
		super();
		this.topic = topic;
		this.partionNum = partionNum;
		this.group = group;
	}

	public void init() {
		logger.info("start consume kafka message");
		ConsumerConnector consumer = createConsumer();
		Map<String, Integer> topicCountMap = new HashMap<>();
		topicCountMap.put(this.topic, partionNum);
		Map<String, List<KafkaStream<byte[], byte[]>>> topicMessageStreams = consumer
				.createMessageStreams(topicCountMap);
		List<KafkaStream<byte[], byte[]>> streams = topicMessageStreams.get(topic);

		threadPool = Executors.newFixedThreadPool(partionNum);
		for (final KafkaStream<byte[], byte[]> partitionMsg : streams) {
			threadPool.execute(new Runnable() {

				@Override
				public void run() {
					ConsumerIterator<byte[], byte[]> iterator = partitionMsg.iterator();
					while (iterator.hasNext()) {
						MessageAndMetadata<byte[], byte[]> msg = iterator.next();
						logger.info("<<== receive message: {}, partition: {}, offset: {}, key: {}", msg.message(),
								msg.partition(), msg.offset(), msg.key());
					}
				}
			});
		}

	}

	private ConsumerConnector createConsumer() {
		Properties properties = new Properties();
		properties.put("zookeeper.connect", "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183");// 声明zk
		properties.put("group.id", this.group);// 必须要使用别的组名称，如果生产者和消费者都在同一组，则不能访问同一组内的topic数据
		return Consumer.createJavaConsumerConnector(new ConsumerConfig(properties));
	}
}
