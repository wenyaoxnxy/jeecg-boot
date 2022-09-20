package org.jeecg.modules.publish.service.impl;

import org.jeecg.modules.publish.entity.ReviewPublish;
import org.jeecg.modules.publish.mapper.ReviewPublishMapper;
import org.jeecg.modules.publish.service.IReviewPublishService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 上线评审需求明细记录
 * @Author: jeecg-boot
 * @Date:   2022-09-20
 * @Version: V1.0
 */
@Service
public class ReviewPublishServiceImpl extends ServiceImpl<ReviewPublishMapper, ReviewPublish> implements IReviewPublishService {
	
	@Autowired
	private ReviewPublishMapper reviewPublishMapper;
	
	@Override
	public List<ReviewPublish> selectByMainId(String mainId) {
		return reviewPublishMapper.selectByMainId(mainId);
	}
}
