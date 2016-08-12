package com.hen.common;
/** 
* @author  liaohenry
* @version 2016年7月28日 上午11:06:40
*/
public interface BaseDao <T extends BaseEntity> {
    int deleteByPrimaryKey(Object id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
}
