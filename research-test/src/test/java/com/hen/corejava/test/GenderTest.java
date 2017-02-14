package com.hen.corejava.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* @author  liaohenry
* @version 2017年1月19日 上午10:31:09
*/
public class GenderTest {
	private static final Logger logger = LoggerFactory.getLogger(GenderTest.class);

	@Test
	public void testGender() {
		logGender(Gender.Male);
		logGender(Gender.Female);
	}
	
	public void logGender(Gender gender) {
		if (gender == Gender.Male) {
			logger.info(Gender.Male.getDesc());
		}
		if (gender == Gender.Female) {
			logger.info(Gender.Female.getDesc());
		}
	}
}
