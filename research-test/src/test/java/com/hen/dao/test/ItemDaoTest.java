package com.hen.dao.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.ImmutableList;
import com.hen.dao.ItemDao;
import com.hen.entity.ItemEntity;

import junit.framework.TestCase;

/** 
* @author  liaohenry
* @version 2016年7月28日 下午5:02:55
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/common-context.xml",
		"classpath:spring/dao-context.xml"
})
public class ItemDaoTest {
	private Logger logger = LoggerFactory.getLogger(ItemDaoTest.class);
	@Autowired
	private ItemDao itemDao;
	
	
	public void testGetName() {
		ItemEntity item = itemDao.selectByPrimaryKey(1);
		logger.info("item: {}", item);
		TestCase.assertTrue(item.getId() == 1);
	}
	
	
	public void testSelectIn() {
		ImmutableList<String> codes = ImmutableList.of("i1", "i2", "i3");
		logger.info("codes: {}", codes.asList());
		List<ItemEntity> entities = itemDao.selectInCodes(codes.asList());
		logger.info("items: {}", entities);
	}
	
	@Test
	public void testSelectWithLimit() {
		List<ItemEntity> entities = itemDao.selectWithLimit(100, 0);
		logger.info("items: {}", entities);
	}
	
	
	public void testInsertSelect() {
		for (int i = 0; i < 10; i++) {
			int count = 0;
			count = itemDao.selectCount();
			logger.info("count before insert: {}", count);

			itemDao.insertSelect();
			
			count = itemDao.selectCount();
			logger.info("count before insert: {}", count);
		}		
	}
	
	
}
