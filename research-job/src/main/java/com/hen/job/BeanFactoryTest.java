package com.hen.job;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import com.hen.dao.ItemDao;

/** 
* @author  liaohenry
* @version 2016年8月3日 上午10:51:13
*/
public class BeanFactoryTest implements BeanFactoryAware {

	private BeanFactory beanFactory;
	
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}
	
	public void fetchData() {
		ItemDao itemDao = beanFactory.getBean(ItemDao.class);
		itemDao.selectByCode("i1");
	}

}
