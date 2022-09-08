package org.jeecg.modules.code.service;

import org.jeecg.modules.code.entity.ReviewCodeDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 代码评审明细记录
 * @Author: jeecg-boot
 * @Date:   2022-08-25
 * @Version: V1.0
 */
public interface IReviewCodeDetailService extends IService<ReviewCodeDetail> {

	public List<ReviewCodeDetail> selectByMainId(String mainId);
}
