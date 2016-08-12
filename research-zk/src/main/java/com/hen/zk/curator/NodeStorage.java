package com.hen.zk.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liaohenry
 * @version 2016年8月9日 下午3:48:31
 */
public class NodeStorage {
	private static final Logger logger = LoggerFactory.getLogger(NodeStorage.class);
	private CuratorInstance curator;

	@SuppressWarnings("static-access")
	public String persistSequential(String path) {
		String res = null;
		try {
			logger.info("persist path: {}", path);
			res = curator.instance().create().creatingParentContainersIfNeeded()
					.withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath(path);
			logger.info("result: {}", res);
		} catch (Exception e) {
			logger.error("{}", e);
		}
		return res;
	}

	@SuppressWarnings("static-access")
	public void persistSequentialWitchCallback(final String path) throws Exception {
		BackgroundCallback callback = new BackgroundCallback() {

			@Override
			public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
				if (event.getResultCode() == KeeperException.Code.OK.intValue()) {
					logger.info("success to persist node path: {}", path);
				} else {
					logger.error("fail to persist node path: {}", path);
				}
			}
		};
		curator.instance().create().creatingParentContainersIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
				.inBackground(callback).forPath(path);
	}
}
