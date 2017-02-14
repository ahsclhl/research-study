package com.hen.mq.kafka;

import kafka.producer.Partitioner;

/**
 * @author liaohenry
 * @version 2016年9月20日 下午6:18:10
 */
public class CidPartitioner implements Partitioner {

	@Override
	public int partition(Object key, int numPartitions) {
		try {
			long id = Long.parseLong(key.toString());
			return (int) Math.abs(id % numPartitions);
		} catch (Exception e) {
			return (int) Math.abs(key.hashCode() % numPartitions);
		}
	}

}
