package org.jeecg.modules.review.service.impl;

import org.jeecg.modules.code.mapper.ReviewCodeChecklistResultMapper;
import org.jeecg.modules.review.entity.ReviewRecord;
import org.jeecg.modules.review.mapper.ReviewRecordMapper;
import org.jeecg.modules.review.service.IReviewRecordService;
import org.jeecg.modules.review.vo.ReviewPushVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 评审记录登记表
 * @Author: jeecg-boot
 * @Date:   2022-08-29
 * @Version: V1.0
 */
@Service
public class ReviewRecordServiceImpl extends ServiceImpl<ReviewRecordMapper, ReviewRecord> implements IReviewRecordService {

    @Autowired
    private ReviewRecordMapper reviewRecordMapper;
    @Override
    public List<ReviewPushVO> selectNeedReviewCode(String planVersion) {
        return reviewRecordMapper.selectNeedReviewCode(planVersion);
    }
    @Override
    public List<ReviewPushVO> selectNeedReviewPublish(String planVersion) {
        return reviewRecordMapper.selectNeedReviewPublish(planVersion);
    }


}
