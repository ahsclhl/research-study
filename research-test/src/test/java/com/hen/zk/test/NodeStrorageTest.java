package com.hen.zk.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hen.zk.curator.NodeStorage;

/** 
* @author  liaohenry
* @version 2016年8月9日 下午4:09:03
* 测试Curator 存储少量信息
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/common-context.xml",
		"classpath:spring/zk-context.xml"
})
public class NodeStrorageTest {
	private NodeStorage nodeStorage = new NodeStorage();
	
	@Test
	public void testPersistSequential() throws Exception {
		for(int i=0; i<3; i++) {
			//nodeStorage.persistSequential("/test/latch");
			nodeStorage.persistSequentialWitchCallback("/test/latch");
		}	
		Thread.sleep(3000);
	}
}
