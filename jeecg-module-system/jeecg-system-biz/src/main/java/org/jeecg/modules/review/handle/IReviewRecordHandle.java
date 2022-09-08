package org.jeecg.modules.review.handle;

import org.jeecg.modules.code.entity.ReviewCode;
import org.jeecg.modules.publish.entity.ReviewPublish;
import org.jeecg.modules.review.entity.ReviewRecord;

/**
 * @Description: 评审记录登记表
 * @Author: jeecg-boot
 * @Date:   2022-08-29
 * @Version: V1.0
 */
public interface IReviewRecordHandle {
    ReviewCode perCheckCodeReview(ReviewRecord reviewRecord);

    ReviewPublish perCheckPublishReview(ReviewRecord reviewRecord);
}
