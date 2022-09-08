package org.jeecg.modules.code.service.impl;

import org.jeecg.modules.code.entity.ReviewCodeDetail;
import org.jeecg.modules.code.mapper.ReviewCodeDetailMapper;
import org.jeecg.modules.code.service.IReviewCodeDetailService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 代码评审明细记录
 * @Author: jeecg-boot
 * @Date:   2022-08-25
 * @Version: V1.0
 */
@Service
public class ReviewCodeDetailServiceImpl extends ServiceImpl<ReviewCodeDetailMapper, ReviewCodeDetail> implements IReviewCodeDetailService {
	
	@Autowired
	private ReviewCodeDetailMapper reviewCodeDetailMapper;
	
	@Override
	public List<ReviewCodeDetail> selectByMainId(String mainId) {
		return reviewCodeDetailMapper.selectByMainId(mainId);
	}
}
