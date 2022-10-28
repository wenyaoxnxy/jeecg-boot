package org.jeecg.modules.review.service;

import org.jeecg.modules.review.entity.ReviewRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.review.vo.ReviewPushVO;

import java.util.List;

/**
 * @Description: 评审记录登记表
 * @Author: jeecg-boot
 * @Date:   2022-08-29
 * @Version: V1.0
 */
public interface IReviewRecordService extends IService<ReviewRecord> {
    public List<ReviewPushVO> selectNeedReviewCode(String planVersion);

    public List<ReviewPushVO> selectNeedReviewPublish(String planVersion);

}
