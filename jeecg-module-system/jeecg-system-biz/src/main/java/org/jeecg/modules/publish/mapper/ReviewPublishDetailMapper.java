package org.jeecg.modules.publish.mapper;

import java.util.List;
import org.jeecg.modules.publish.entity.ReviewPublishDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 上线评审明细记录
 * @Author: jeecg-boot
 * @Date:   2022-08-25
 * @Version: V1.0
 */
public interface ReviewPublishDetailMapper extends BaseMapper<ReviewPublishDetail> {

	public boolean deleteByMainId(String mainId);
    
	public List<ReviewPublishDetail> selectByMainId(String mainId);
}
