package org.jeecg.modules.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.modules.code.entity.ReviewCode;
import org.jeecg.modules.code.entity.ReviewCodeDetail;
import org.jeecg.modules.code.entity.ReviewCodeChecklistResult;
import org.jeecg.modules.code.mapper.ReviewCodeDetailMapper;
import org.jeecg.modules.code.mapper.ReviewCodeChecklistResultMapper;
import org.jeecg.modules.code.mapper.ReviewCodeMapper;
import org.jeecg.modules.code.service.IReviewCodeService;
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
 * @Description: 代码评审记录
 * @Author: jeecg-boot
 * @Date:   2022-08-25
 * @Version: V1.0
 */
@Service
public class ReviewCodeServiceImpl extends ServiceImpl<ReviewCodeMapper, ReviewCode> implements IReviewCodeService {

	@Autowired
	private ReviewCodeMapper reviewCodeMapper;
	@Autowired
	private ReviewCodeDetailMapper reviewCodeDetailMapper;
	@Autowired
	private ReviewCodeChecklistResultMapper reviewCodeChecklistResultMapper;
	@Autowired
	private IReviewRecordService reviewRecordService;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveMain(ReviewCode reviewCode, List<ReviewCodeDetail> reviewCodeDetailList,List<ReviewCodeChecklistResult> reviewCodeChecklistResultList) {
		reviewCodeMapper.insert(reviewCode);
		for(ReviewCodeDetail entity:reviewCodeDetailList) {
			//外键设置
			entity.setRefId(reviewCode.getId());
			reviewCodeDetailMapper.insert(entity);
		}
		for(ReviewCodeChecklistResult entity:reviewCodeChecklistResultList) {
			//外键设置
			entity.setRefId(reviewCode.getId());
			reviewCodeChecklistResultMapper.insert(entity);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateMain(ReviewCode reviewCode,List<ReviewCodeDetail> reviewCodeDetailList,List<ReviewCodeChecklistResult> reviewCodeChecklistResultList) {
		reviewCodeMapper.updateById(reviewCode);
		
		//1.先删除子表数据
		reviewCodeDetailMapper.deleteByMainId(reviewCode.getId());
		reviewCodeChecklistResultMapper.deleteByMainId(reviewCode.getId());
		
		//2.子表数据重新插入
		for(ReviewCodeDetail entity:reviewCodeDetailList) {
			//外键设置
			entity.setRefId(reviewCode.getId());
			reviewCodeDetailMapper.insert(entity);
		}
		for(ReviewCodeChecklistResult entity:reviewCodeChecklistResultList) {
			//外键设置
			entity.setRefId(reviewCode.getId());
			reviewCodeChecklistResultMapper.insert(entity);
		}

		//更新主评审记录表为已完成
		QueryWrapper<ReviewRecord> queryWrapper = new QueryWrapper<ReviewRecord>();
		queryWrapper.lambda().eq(ReviewRecord::getXqNumber,reviewCode.getXqNumber()).eq(ReviewRecord::getKjxqNum,reviewCode.getKjxqNum()).eq(ReviewRecord::getIttaskNum,reviewCode.getIttaskNum());

		ReviewRecord record = reviewRecordService.getOne(queryWrapper);
		record.setReviewCode("2");
		reviewRecordService.updateById(record);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delMain(String id) {
		reviewCodeDetailMapper.deleteByMainId(id);
		reviewCodeChecklistResultMapper.deleteByMainId(id);
		reviewCodeMapper.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			reviewCodeDetailMapper.deleteByMainId(id.toString());
			reviewCodeChecklistResultMapper.deleteByMainId(id.toString());
			reviewCodeMapper.deleteById(id);
		}
	}
	
}
