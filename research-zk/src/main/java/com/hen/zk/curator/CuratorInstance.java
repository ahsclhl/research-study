package com.hen.zk.curator;

import javax.annotation.Resource;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hen.config.SystemConfig;

/**
 * @author liaohenry
 * @version 2016年8月1日 下午2:25:28
 */
public class CuratorInstance {
	private static final Logger logger = LoggerFactory.getLogger(CuratorInstance.class);
	
	@Resource(name = "SystemConfig")
	private SystemConfig systemConfig;

	private CuratorInstance() {

	}

	private static class SingleInstance {
		public static CuratorFramework instance = CuratorFrameworkFactory
				.newClient(SystemConfig.getConfig("zk.address"), 1000, 1000, new ExponentialBackoffRetry(1000, 3));
		static {
			logger.info("SingleInstance start");
			instance.start();
		}
	}

	public static CuratorFramework instance() {
		return SingleInstance.instance;
	}
}
