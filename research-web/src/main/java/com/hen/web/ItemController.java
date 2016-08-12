package com.hen.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hen.dao.ItemDao;
import com.hen.entity.ItemEntity;

/** 
* @author  liaohenry
* @version 2016年7月28日 下午3:38:29
*/
@Controller
@RequestMapping("item")
public class ItemController {
	private Logger logger = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	private ItemDao itemDao;
	
	@ResponseBody
	@RequestMapping(value = "name", method = RequestMethod.GET)
	public String getName(String code) {
		logger.info("getName: {}", code);
		ItemEntity item = itemDao.selectByPrimaryKey(1); //itemService.get(code);
		logger.info("item: {}", item);
		return item.getName();
	}
}
