package org.jeecg.modules.publish.mapper;

import java.util.List;
import org.jeecg.modules.publish.entity.ReviewPublish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 上线评审需求明细记录
 * @Author: jeecg-boot
 * @Date:   2022-09-20
 * @Version: V1.0
 */
public interface ReviewPublishMapper extends BaseMapper<ReviewPublish> {

	public boolean deleteByMainId(String mainId);
    
	public List<ReviewPublish> selectByMainId(String mainId);
}
