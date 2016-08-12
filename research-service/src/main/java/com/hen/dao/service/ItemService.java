package com.hen.dao.service;
import java.util.List;

/** 
* @author  liaohenry
* @version 2016年7月28日 上午11:38:30
*/
import com.hen.entity.ItemEntity;

public interface ItemService {
	void add();
	ItemEntity get(String code);
	List<ItemEntity> selectWithLimit(int size, int lastRowNum);
}
