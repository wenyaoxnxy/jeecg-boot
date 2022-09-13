package org.jeecg.modules.design.mapper;

import java.util.List;
import org.jeecg.modules.design.entity.ReviewDesignDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 概设评审明细记录
 * @Author: jeecg-boot
 * @Date:   2022-09-13
 * @Version: V1.0
 */
public interface ReviewDesignDetailMapper extends BaseMapper<ReviewDesignDetail> {

	public boolean deleteByMainId(String mainId);
    
	public List<ReviewDesignDetail> selectByMainId(String mainId);
}
