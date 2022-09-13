package org.jeecg.modules.design.service.impl;

import org.jeecg.modules.design.entity.ReviewDesignDetail;
import org.jeecg.modules.design.mapper.ReviewDesignDetailMapper;
import org.jeecg.modules.design.service.IReviewDesignDetailService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 概设评审明细记录
 * @Author: jeecg-boot
 * @Date:   2022-09-13
 * @Version: V1.0
 */
@Service
public class ReviewDesignDetailServiceImpl extends ServiceImpl<ReviewDesignDetailMapper, ReviewDesignDetail> implements IReviewDesignDetailService {
	
	@Autowired
	private ReviewDesignDetailMapper reviewDesignDetailMapper;
	
	@Override
	public List<ReviewDesignDetail> selectByMainId(String mainId) {
		return reviewDesignDetailMapper.selectByMainId(mainId);
	}
}
