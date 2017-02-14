package com.hen.eureka.service;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 
* @author  liaohenry
* @version 2016年10月28日 上午11:06:19
*/
@SpringBootApplication
@EnableAutoConfiguration
@EnableEurekaClient
@RestController
public class Application {
	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).web(true).run(args);
	}
	
	@RequestMapping("/")
	public String home() {
		return "henry's service";
	}
}
