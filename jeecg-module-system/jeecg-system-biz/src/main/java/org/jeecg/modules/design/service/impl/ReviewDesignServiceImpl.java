package org.jeecg.modules.design.service.impl;

import org.jeecg.modules.design.entity.ReviewDesign;
import org.jeecg.modules.design.entity.ReviewDesignDetail;
import org.jeecg.modules.design.mapper.ReviewDesignDetailMapper;
import org.jeecg.modules.design.mapper.ReviewDesignMapper;
import org.jeecg.modules.design.service.IReviewDesignService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 概设评审记录
 * @Author: jeecg-boot
 * @Date:   2022-09-13
 * @Version: V1.0
 */
@Service
public class ReviewDesignServiceImpl extends ServiceImpl<ReviewDesignMapper, ReviewDesign> implements IReviewDesignService {

	@Autowired
	private ReviewDesignMapper reviewDesignMapper;
	@Autowired
	private ReviewDesignDetailMapper reviewDesignDetailMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveMain(ReviewDesign reviewDesign, List<ReviewDesignDetail> reviewDesignDetailList) {
		reviewDesignMapper.insert(reviewDesign);
		for(ReviewDesignDetail entity:reviewDesignDetailList) {
			//外键设置
			entity.setRefId(reviewDesign.getId());
			reviewDesignDetailMapper.insert(entity);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateMain(ReviewDesign reviewDesign,List<ReviewDesignDetail> reviewDesignDetailList) {
		reviewDesignMapper.updateById(reviewDesign);
		
		//1.先删除子表数据
		reviewDesignDetailMapper.deleteByMainId(reviewDesign.getId());
		
		//2.子表数据重新插入
		for(ReviewDesignDetail entity:reviewDesignDetailList) {
			//外键设置
			entity.setRefId(reviewDesign.getId());
			reviewDesignDetailMapper.insert(entity);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delMain(String id) {
		reviewDesignDetailMapper.deleteByMainId(id);
		reviewDesignMapper.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			reviewDesignDetailMapper.deleteByMainId(id.toString());
			reviewDesignMapper.deleteById(id);
		}
	}
	
}
