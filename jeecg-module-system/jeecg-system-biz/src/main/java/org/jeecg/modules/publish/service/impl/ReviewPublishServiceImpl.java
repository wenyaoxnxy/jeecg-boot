package org.jeecg.modules.publish.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.modules.publish.entity.ReviewPublish;
import org.jeecg.modules.publish.entity.ReviewPublishDetail;
import org.jeecg.modules.publish.entity.ReviewPublishChecklistResult;
import org.jeecg.modules.publish.mapper.ReviewPublishDetailMapper;
import org.jeecg.modules.publish.mapper.ReviewPublishChecklistResultMapper;
import org.jeecg.modules.publish.mapper.ReviewPublishMapper;
import org.jeecg.modules.publish.service.IReviewPublishService;
import org.jeecg.modules.review.entity.ReviewRecord;
import org.jeecg.modules.review.service.IReviewRecordService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 上线评审记录
 * @Author: jeecg-boot
 * @Date:   2022-08-25
 * @Version: V1.0
 */
@Service
public class ReviewPublishServiceImpl extends ServiceImpl<ReviewPublishMapper, ReviewPublish> implements IReviewPublishService {

	@Autowired
	private ReviewPublishMapper reviewPublishMapper;
	@Autowired
	private ReviewPublishDetailMapper reviewPublishDetailMapper;
	@Autowired
	private ReviewPublishChecklistResultMapper reviewPublishChecklistResultMapper;
	@Autowired
	private IReviewRecordService reviewRecordService;
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveMain(ReviewPublish reviewPublish, List<ReviewPublishDetail> reviewPublishDetailList,List<ReviewPublishChecklistResult> reviewPublishChecklistResultList) {
		reviewPublishMapper.insert(reviewPublish);
		for(ReviewPublishDetail entity:reviewPublishDetailList) {
			//外键设置
			entity.setRefId(reviewPublish.getId());
			reviewPublishDetailMapper.insert(entity);
		}
		for(ReviewPublishChecklistResult entity:reviewPublishChecklistResultList) {
			//外键设置
			entity.setRefId(reviewPublish.getId());
			reviewPublishChecklistResultMapper.insert(entity);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateMain(ReviewPublish reviewPublish,List<ReviewPublishDetail> reviewPublishDetailList,List<ReviewPublishChecklistResult> reviewPublishChecklistResultList) {
		reviewPublishMapper.updateById(reviewPublish);
		
		//1.先删除子表数据
		reviewPublishDetailMapper.deleteByMainId(reviewPublish.getId());
		reviewPublishChecklistResultMapper.deleteByMainId(reviewPublish.getId());
		
		//2.子表数据重新插入
		for(ReviewPublishDetail entity:reviewPublishDetailList) {
			//外键设置
			entity.setRefId(reviewPublish.getId());
			reviewPublishDetailMapper.insert(entity);
		}
		for(ReviewPublishChecklistResult entity:reviewPublishChecklistResultList) {
			//外键设置
			entity.setRefId(reviewPublish.getId());
			reviewPublishChecklistResultMapper.insert(entity);
		}

		//更新主评审记录表为已完成
		QueryWrapper<ReviewRecord> queryWrapper = new QueryWrapper<ReviewRecord>();
		queryWrapper.lambda().eq(ReviewRecord::getXqNumber,reviewPublish.getXqNumber()).eq(ReviewRecord::getKjxqNum,reviewPublish.getKjxqNum()).eq(ReviewRecord::getIttaskNum,reviewPublish.getIttaskNum());

		ReviewRecord record = reviewRecordService.getOne(queryWrapper);
		record.setReviewPublish("2");
		reviewRecordService.updateById(record);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delMain(String id) {
		reviewPublishDetailMapper.deleteByMainId(id);
		reviewPublishChecklistResultMapper.deleteByMainId(id);
		reviewPublishMapper.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			reviewPublishDetailMapper.deleteByMainId(id.toString());
			reviewPublishChecklistResultMapper.deleteByMainId(id.toString());
			reviewPublishMapper.deleteById(id);
		}
	}
	
}
