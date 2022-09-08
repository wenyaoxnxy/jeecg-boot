package org.jeecg.modules.code.service.impl;

import org.jeecg.modules.code.entity.ReviewCodeChecklistResult;
import org.jeecg.modules.code.mapper.ReviewCodeChecklistResultMapper;
import org.jeecg.modules.code.service.IReviewCodeChecklistResultService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 代码评审检查清单检查结果
 * @Author: jeecg-boot
 * @Date:   2022-08-25
 * @Version: V1.0
 */
@Service
public class ReviewCodeChecklistResultServiceImpl extends ServiceImpl<ReviewCodeChecklistResultMapper, ReviewCodeChecklistResult> implements IReviewCodeChecklistResultService {
	
	@Autowired
	private ReviewCodeChecklistResultMapper reviewCodeChecklistResultMapper;
	
	@Override
	public List<ReviewCodeChecklistResult> selectByMainId(String mainId) {
		return reviewCodeChecklistResultMapper.selectByMainId(mainId);
	}
}
