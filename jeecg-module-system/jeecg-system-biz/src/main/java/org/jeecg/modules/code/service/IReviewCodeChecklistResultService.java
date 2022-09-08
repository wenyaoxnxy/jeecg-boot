package org.jeecg.modules.code.service;

import org.jeecg.modules.code.entity.ReviewCodeChecklistResult;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 代码评审检查清单检查结果
 * @Author: jeecg-boot
 * @Date:   2022-08-25
 * @Version: V1.0
 */
public interface IReviewCodeChecklistResultService extends IService<ReviewCodeChecklistResult> {

	public List<ReviewCodeChecklistResult> selectByMainId(String mainId);
}
