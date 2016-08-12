package com.hen.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hen.common.BaseDao;
import com.hen.common.MyBatisDao;
import com.hen.entity.ItemEntity;

/** 
* @author  liaohenry
* @version 2016年7月28日 上午11:23:34
*/
@MyBatisDao
public interface ItemDao extends BaseDao<ItemEntity> {
	ItemEntity selectByCode(String code);
	List<ItemEntity> selectInCodes(@Param("codes")List<String> codes);
	List<ItemEntity> selectWithLimit(@Param("size")int size, @Param("lastRowNum")int lastRowNum);
	void insertSelect();
	int selectCount();
}
