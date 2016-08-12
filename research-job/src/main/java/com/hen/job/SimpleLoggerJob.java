package com.hen.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;

/** 
* @author  liaohenry
* @version 2016年7月29日 下午3:17:58
*/
public class SimpleLoggerJob extends AbstractSimpleElasticJob {
	private Logger logger = LoggerFactory.getLogger(SimpleLoggerJob.class);

	@Override
	public void process(JobExecutionMultipleShardingContext context) {
		logger.info("processing -- context: {}", context);	
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			logger.error("{}", e);
		}
		logger.info("processing -- job: {} -- end", context.getJobName());
	}
	
}
