package org.jeecg.modules.publish.mapper;

import java.util.List;
import org.jeecg.modules.publish.entity.ReviewPublishChecklistResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 上线评审检查清单记录列表
 * @Author: jeecg-boot
 * @Date:   2022-09-20
 * @Version: V1.0
 */
public interface ReviewPublishChecklistResultMapper extends BaseMapper<ReviewPublishChecklistResult> {

	public boolean deleteByMainId(String mainId);
    
	public List<ReviewPublishChecklistResult> selectByMainId(String mainId);
}
