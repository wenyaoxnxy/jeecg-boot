package org.jeecg.modules.publish.service.impl;

import org.jeecg.modules.publish.entity.ReviewPublishDetail;
import org.jeecg.modules.publish.mapper.ReviewPublishDetailMapper;
import org.jeecg.modules.publish.service.IReviewPublishDetailService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 上线评审问题记录
 * @Author: jeecg-boot
 * @Date:   2022-09-20
 * @Version: V1.0
 */
@Service
public class ReviewPublishDetailServiceImpl extends ServiceImpl<ReviewPublishDetailMapper, ReviewPublishDetail> implements IReviewPublishDetailService {
	
	@Autowired
	private ReviewPublishDetailMapper reviewPublishDetailMapper;
	
	@Override
	public List<ReviewPublishDetail> selectByMainId(String mainId) {
		return reviewPublishDetailMapper.selectByMainId(mainId);
	}
}
