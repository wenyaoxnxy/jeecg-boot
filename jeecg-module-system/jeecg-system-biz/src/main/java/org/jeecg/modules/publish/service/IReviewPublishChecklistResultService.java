package org.jeecg.modules.publish.service;

import org.jeecg.modules.publish.entity.ReviewPublishChecklistResult;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 上线评审检查清单检查结果
 * @Author: jeecg-boot
 * @Date:   2022-08-25
 * @Version: V1.0
 */
public interface IReviewPublishChecklistResultService extends IService<ReviewPublishChecklistResult> {

	public List<ReviewPublishChecklistResult> selectByMainId(String mainId);
}
