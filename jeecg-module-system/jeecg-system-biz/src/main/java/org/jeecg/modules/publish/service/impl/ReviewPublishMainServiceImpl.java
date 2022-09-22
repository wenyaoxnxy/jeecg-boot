package org.jeecg.modules.publish.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.modules.publish.entity.ReviewPublishMain;
import org.jeecg.modules.publish.entity.ReviewPublish;
import org.jeecg.modules.publish.entity.ReviewPublishDetail;
import org.jeecg.modules.publish.entity.ReviewPublishChecklistResult;
import org.jeecg.modules.publish.mapper.ReviewPublishMapper;
import org.jeecg.modules.publish.mapper.ReviewPublishDetailMapper;
import org.jeecg.modules.publish.mapper.ReviewPublishChecklistResultMapper;
import org.jeecg.modules.publish.mapper.ReviewPublishMainMapper;
import org.jeecg.modules.publish.service.IReviewPublishMainService;
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
 * @Description: 上线评审系统记录
 * @Author: jeecg-boot
 * @Date:   2022-09-20
 * @Version: V1.0
 */
@Service
public class ReviewPublishMainServiceImpl extends ServiceImpl<ReviewPublishMainMapper, ReviewPublishMain> implements IReviewPublishMainService {

	@Autowired
	private ReviewPublishMainMapper reviewPublishMainMapper;
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
	public void saveMain(ReviewPublishMain reviewPublishMain, List<ReviewPublish> reviewPublishList,List<ReviewPublishDetail> reviewPublishDetailList,List<ReviewPublishChecklistResult> reviewPublishChecklistResultList) {
		reviewPublishMainMapper.insert(reviewPublishMain);
		for(ReviewPublish entity:reviewPublishList) {
			//外键设置
			entity.setRefId(reviewPublishMain.getId());
			reviewPublishMapper.insert(entity);
		}
		for(ReviewPublishDetail entity:reviewPublishDetailList) {
			//外键设置
			entity.setRefId(reviewPublishMain.getId());
			reviewPublishDetailMapper.insert(entity);
		}
		for(ReviewPublishChecklistResult entity:reviewPublishChecklistResultList) {
			//外键设置
			entity.setRefId(reviewPublishMain.getId());
			reviewPublishChecklistResultMapper.insert(entity);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateMain(ReviewPublishMain reviewPublishMain,List<ReviewPublish> reviewPublishList,List<ReviewPublishDetail> reviewPublishDetailList,List<ReviewPublishChecklistResult> reviewPublishChecklistResultList) {
		reviewPublishMainMapper.updateById(reviewPublishMain);
		
		//1.先删除子表数据
		reviewPublishMapper.deleteByMainId(reviewPublishMain.getId());
		reviewPublishDetailMapper.deleteByMainId(reviewPublishMain.getId());
		reviewPublishChecklistResultMapper.deleteByMainId(reviewPublishMain.getId());
		
		//2.子表数据重新插入
		for(ReviewPublish entity:reviewPublishList) {
			//外键设置
			entity.setRefId(reviewPublishMain.getId());
			reviewPublishMapper.insert(entity);
		}
		for(ReviewPublishDetail entity:reviewPublishDetailList) {
			//外键设置
			entity.setRefId(reviewPublishMain.getId());
			reviewPublishDetailMapper.insert(entity);
		}
		for(ReviewPublishChecklistResult entity:reviewPublishChecklistResultList) {
			//外键设置
			entity.setRefId(reviewPublishMain.getId());
			reviewPublishChecklistResultMapper.insert(entity);
		}

		//更新主评审记录表为已完成
		QueryWrapper<ReviewRecord> queryWrapper = new QueryWrapper<ReviewRecord>();
		queryWrapper.lambda().eq(ReviewRecord::getSystems,reviewPublishMain.getSystems()).eq(ReviewRecord::getVersionplan,reviewPublishMain.getVersionplan());

		List<ReviewRecord> record = reviewRecordService.list(queryWrapper);
		for (ReviewRecord reviewRecord:record) {
			reviewRecord.setReviewPublish("2");
			reviewRecordService.updateById(reviewRecord);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delMain(String id) {
		reviewPublishMapper.deleteByMainId(id);
		reviewPublishDetailMapper.deleteByMainId(id);
		reviewPublishChecklistResultMapper.deleteByMainId(id);
		reviewPublishMainMapper.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			reviewPublishMapper.deleteByMainId(id.toString());
			reviewPublishDetailMapper.deleteByMainId(id.toString());
			reviewPublishChecklistResultMapper.deleteByMainId(id.toString());
			reviewPublishMainMapper.deleteById(id);
		}
	}
	
}
