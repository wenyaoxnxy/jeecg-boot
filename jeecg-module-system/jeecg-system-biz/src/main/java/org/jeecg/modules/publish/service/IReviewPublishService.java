package org.jeecg.modules.publish.service;

import org.jeecg.modules.publish.entity.ReviewPublish;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 上线评审需求明细记录
 * @Author: jeecg-boot
 * @Date:   2022-09-20
 * @Version: V1.0
 */
public interface IReviewPublishService extends IService<ReviewPublish> {

	public List<ReviewPublish> selectByMainId(String mainId);
}
