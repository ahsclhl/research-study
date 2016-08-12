package com.hen.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/** 
* @author  liaohenry
* @version 2016年8月1日 下午2:26:01
*/
@Component("SystemConfig")
public class SystemConfig implements InitializingBean {

	private static final Map<String, String> config = new HashMap<>();
	@Resource(name = "system")
	private  Properties sysProperties;
	@Override
	public void afterPropertiesSet() throws Exception {
		for (Entry<Object, Object> entry : sysProperties.entrySet()) {
			config.put(entry.getKey().toString(), entry.getValue().toString());
		}
	}
	
	public static String getConfig(String key) {
		return config.get(key);
	}
	
	public static Map<String, String> getConfig() {
		return config;
	}

}
