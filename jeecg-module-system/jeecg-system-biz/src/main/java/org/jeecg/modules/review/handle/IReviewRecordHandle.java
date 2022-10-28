package org.jeecg.modules.review.handle;

import org.jeecg.modules.code.entity.ReviewCode;
import org.jeecg.modules.design.entity.ReviewDesign;
import org.jeecg.modules.publish.entity.ReviewPublishMain;
import org.jeecg.modules.review.entity.ReviewRecord;

/**
 * @Description: 评审记录登记表
 * @Author: jeecg-boot
 * @Date:   2022-08-29
 * @Version: V1.0
 */
public interface IReviewRecordHandle {
    ReviewCode perCheckCodeReview(ReviewRecord reviewRecord);

    ReviewPublishMain perCheckPublishReview(ReviewRecord reviewRecord);

    ReviewDesign perDesignReview(ReviewRecord reviewRecord);

    void reviewInfoPush(String versionPlan);
}
