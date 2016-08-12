package com.hen.job;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.exception.JobException;
import com.dangdang.ddframe.job.plugin.job.type.dataflow.AbstractBatchThroughputDataFlowElasticJob;
import com.hen.dao.ItemDao;
import com.hen.entity.ItemEntity;

/** 
* @author  liaohenry
* @version 2016年8月2日 下午2:52:04
*/
@Component
public class ThroughputJob extends AbstractBatchThroughputDataFlowElasticJob<ItemEntity> {

	private final static Logger logger = LoggerFactory.getLogger(ThroughputJob.class);
	@Autowired
	private ItemDao itemDao;
	
	private AtomicInteger lastRowNum = new AtomicInteger(0);
	
	@Override
	public int processData(JobExecutionMultipleShardingContext shardingContext, List<ItemEntity> data) {
		logger.info("processing data");
		logger.info("shardingContext :{}", shardingContext);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			logger.error("processing data exception: {}", e);
		}
		logger.info("processing -- last id: {}", data.get(data.size()-1).getId());
		
		return 0;
	}

	@Override
	public List<ItemEntity> fetchData(JobExecutionMultipleShardingContext shardingContext) {
		logger.info("fetch data");
		logger.info("shardingContext :{}", shardingContext);
		List<ItemEntity> entities = itemDao.selectWithLimit(300, lastRowNum.getAndAdd(300));
		if (entities.size() == 0) {
			logger.info("fetch null");
			return null;
		}
		logger.info("fetch -- last id: {}", entities.get(entities.size()-1).getId());
		return entities;
	}

    @Override
    public void handleJobExecutionException(final JobException jobException) {
    	logger.info("job exception: {}", jobException);
    }

}
