package com.hen.util.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author liaohenry
 * @version 2016年9月13日 上午11:02:41
 */
public class JSONTest {
	private static final Logger logger = LoggerFactory.getLogger(JSONTest.class);
	private static final String JSON_STR = "{\"OrderTag\":[\"pay_specify_ffan\", \"pay_specify_no_pocket\",\"pay_specify_no_promotion\"]}";

	@Test
	public void testList() {
		JSONObject obj = JSONObject.parseObject(JSON_STR);
		String tagStr = String.valueOf(obj.get("OrderTag"));
		logger.info("OrderTag: {}", tagStr);
		JSONArray tagObj = JSONArray.parseArray(tagStr);
		logger.info("Size of OrderTag: {}", tagObj.size());
		logger.info("OrderTag Object: {}", tagObj);
		
		long discountCoupon = 0;
		JSONObject discount = new JSONObject();
		discount.put("discountCoupon", discountCoupon);
		discount.put("discountPromotion", 0);
		discount.put("discountAmount", discountCoupon);
		obj.put("discountPrice", discount);
		logger.info("remark JSON is:{}", obj.toJSONString());
	}
}
