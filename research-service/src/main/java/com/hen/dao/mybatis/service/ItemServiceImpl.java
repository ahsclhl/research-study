package com.hen.dao.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hen.dao.ItemDao;
import com.hen.dao.service.ItemService;
import com.hen.entity.ItemEntity;

/** 
* @author  liaohenry
* @version 2016年7月28日 上午11:37:50
*/
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemDao itemDao;
	
	@Override
	public void add() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ItemEntity get(String code) {
		return itemDao.selectByCode(code);
	}

	@Override
	public List<ItemEntity> selectWithLimit(int size, int lastRowNum) {
		return itemDao.selectWithLimit(size, lastRowNum);
	}

}
