package org.jeecg.modules.design.service;

import org.jeecg.modules.design.entity.ReviewDesignDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 概设评审明细记录
 * @Author: jeecg-boot
 * @Date:   2022-09-13
 * @Version: V1.0
 */
public interface IReviewDesignDetailService extends IService<ReviewDesignDetail> {

	public List<ReviewDesignDetail> selectByMainId(String mainId);
}
