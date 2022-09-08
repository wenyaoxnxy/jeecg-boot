package org.jeecg.modules.publish.service.impl;

import org.jeecg.modules.publish.entity.ReviewPublishChecklistResult;
import org.jeecg.modules.publish.mapper.ReviewPublishChecklistResultMapper;
import org.jeecg.modules.publish.service.IReviewPublishChecklistResultService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 上线评审检查清单检查结果
 * @Author: jeecg-boot
 * @Date:   2022-08-25
 * @Version: V1.0
 */
@Service
public class ReviewPublishChecklistResultServiceImpl extends ServiceImpl<ReviewPublishChecklistResultMapper, ReviewPublishChecklistResult> implements IReviewPublishChecklistResultService {
	
	@Autowired
	private ReviewPublishChecklistResultMapper reviewPublishChecklistResultMapper;
	
	@Override
	public List<ReviewPublishChecklistResult> selectByMainId(String mainId) {
		return reviewPublishChecklistResultMapper.selectByMainId(mainId);
	}
}
