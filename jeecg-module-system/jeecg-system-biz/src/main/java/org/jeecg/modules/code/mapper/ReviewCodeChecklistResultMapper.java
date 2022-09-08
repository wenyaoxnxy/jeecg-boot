package org.jeecg.modules.code.mapper;

import java.util.List;
import org.jeecg.modules.code.entity.ReviewCodeChecklistResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 代码评审检查清单检查结果
 * @Author: jeecg-boot
 * @Date:   2022-08-25
 * @Version: V1.0
 */
public interface ReviewCodeChecklistResultMapper extends BaseMapper<ReviewCodeChecklistResult> {

	public boolean deleteByMainId(String mainId);
    
	public List<ReviewCodeChecklistResult> selectByMainId(String mainId);
}
