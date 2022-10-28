package org.jeecg.modules.review.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.code.entity.ReviewCodeChecklistResult;
import org.jeecg.modules.review.entity.ReviewRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.review.vo.ReviewPushVO;

/**
 * @Description: 评审记录登记表
 * @Author: jeecg-boot
 * @Date:   2022-08-29
 * @Version: V1.0
 */
public interface ReviewRecordMapper extends BaseMapper<ReviewRecord> {
    public List<ReviewPushVO> selectNeedReviewCode(String planVersion);

    public List<ReviewPushVO> selectNeedReviewPublish(String planVersion);
}
