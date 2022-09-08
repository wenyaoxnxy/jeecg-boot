package org.jeecg.modules.publish.service;

import org.jeecg.modules.publish.entity.ReviewPublishDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 上线评审明细记录
 * @Author: jeecg-boot
 * @Date:   2022-08-25
 * @Version: V1.0
 */
public interface IReviewPublishDetailService extends IService<ReviewPublishDetail> {

	public List<ReviewPublishDetail> selectByMainId(String mainId);
}
