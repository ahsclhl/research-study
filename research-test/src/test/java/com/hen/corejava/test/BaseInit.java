package com.hen.corejava.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* @author  liaohenry
* @version 2016年12月12日 上午9:57:44
*/
public class BaseInit {
	private static final Logger logger = LoggerFactory.getLogger(BaseInit.class);
	private String baseStr1;
	private String baseStr2;
	private static String staticBaseStr1;
	
	static {
		staticBaseStr1 = "staticBaseStr1";
		logger.info(staticBaseStr1);
	}
	
	{
		baseStr1 = "baseStr1";
		logger.info(baseStr1);
	}
	
	public BaseInit() {
		baseStr2 = "baseStr2";
		logger.info(baseStr2);
	}
	
}
