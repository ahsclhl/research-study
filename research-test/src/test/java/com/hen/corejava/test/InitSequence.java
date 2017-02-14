package com.hen.corejava.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* @author  liaohenry
* @version 2016年12月12日 上午9:11:14
* init sequence：staticBaseStr1 -> staticStr1 -> blockStaticStr2 -> baseStr1 -> baseStr2 -> str1 -> blockStr2 -> constructorStr3
* static 变量（基类、变量定义、static block）
* ->
* 基类成员
* ->
* 自身成员
*/
public class InitSequence extends BaseInit {
	private static final Logger logger = LoggerFactory.getLogger(InitSequence.class);
	private String str1 = "str1";
	private String blockStr2;
	private String constructorStr3;
	
	private static String staticStr1 = "staticStr1";
	private static String blockStaticStr2;
	
	{
		blockStr2 = "str2";
		logger.info(blockStr2);
	}
	
	static {
		blockStaticStr2 = "staticStr2";
		logger.info(blockStaticStr2);
	}
	
	public InitSequence() {
		constructorStr3 = "str3";
		logger.info(constructorStr3);
	}
}
