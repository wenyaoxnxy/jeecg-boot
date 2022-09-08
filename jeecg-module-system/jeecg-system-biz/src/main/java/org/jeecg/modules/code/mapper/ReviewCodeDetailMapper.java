package org.jeecg.modules.code.mapper;

import java.util.List;
import org.jeecg.modules.code.entity.ReviewCodeDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 代码评审明细记录
 * @Author: jeecg-boot
 * @Date:   2022-08-25
 * @Version: V1.0
 */
public interface ReviewCodeDetailMapper extends BaseMapper<ReviewCodeDetail> {

	public boolean deleteByMainId(String mainId);
    
	public List<ReviewCodeDetail> selectByMainId(String mainId);
}
