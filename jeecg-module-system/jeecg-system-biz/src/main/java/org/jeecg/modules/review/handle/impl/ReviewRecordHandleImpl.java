package org.jeecg.modules.review.handle.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.modules.code.entity.ReviewCode;
import org.jeecg.modules.code.entity.ReviewCodeChecklistResult;
import org.jeecg.modules.code.service.IReviewCodeChecklistResultService;
import org.jeecg.modules.code.service.IReviewCodeDetailService;
import org.jeecg.modules.code.service.IReviewCodeService;
import org.jeecg.modules.publish.entity.ReviewPublish;
import org.jeecg.modules.publish.entity.ReviewPublishChecklistResult;
import org.jeecg.modules.publish.service.IReviewPublishChecklistResultService;
import org.jeecg.modules.publish.service.IReviewPublishDetailService;
import org.jeecg.modules.publish.service.IReviewPublishService;
import org.jeecg.modules.review.entity.ReviewRecord;
import org.jeecg.modules.review.handle.IReviewRecordHandle;
import org.jeecg.modules.review.service.IReviewRecordService;
import org.jeecg.modules.rules.entity.CodeReviewChecklistRules;
import org.jeecg.modules.rules.entity.PublishReviewChecklistRules;
import org.jeecg.modules.rules.service.ICodeReviewChecklistRulesService;
import org.jeecg.modules.rules.service.IPublishReviewChecklistRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Description: 评审记录登记表
 * @Author: jeecg-boot
 * @Date:   2022-08-29
 * @Version: V1.0
 */
@Service
@Slf4j
public class ReviewRecordHandleImpl implements IReviewRecordHandle {

    @Autowired
    private IReviewRecordService reviewRecordService;

    @Autowired
    private IReviewCodeService reviewCodeService;


    @Autowired
    private IReviewCodeDetailService reviewCodeDetailService;

    @Autowired
    private IReviewCodeChecklistResultService reviewCodeChecklistResultService;

    @Autowired
    private ICodeReviewChecklistRulesService codeReviewChecklistRulesService;

    @Autowired
    private IReviewPublishService reviewPublishService;

    @Autowired
    private IReviewPublishDetailService reviewPublishDetailService;

    @Autowired
    private IReviewPublishChecklistResultService reviewPublishChecklistResultService;

    @Autowired
    private IPublishReviewChecklistRulesService publishReviewChecklistRulesService;

    @Override
    @Transactional
    public ReviewCode perCheckCodeReview(ReviewRecord reviewRecord) {
       if(StringUtils.isAnyEmpty(reviewRecord.getXqNumber(),reviewRecord.getKjxqNum(),reviewRecord.getIttaskNum())){
            throw new JeecgBootException("输入需求内容为空");
       }
       QueryWrapper<ReviewCode> queryWrapper = new QueryWrapper<ReviewCode>();
       queryWrapper.lambda().eq(ReviewCode::getXqNumber,reviewRecord.getXqNumber()).eq(ReviewCode::getKjxqNum,reviewRecord.getKjxqNum()).eq(ReviewCode::getIttaskNum,reviewRecord.getIttaskNum());
       List<ReviewCode> reviewCodeList =  reviewCodeService.list(queryWrapper);
//       if(reviewCodeList != null && reviewCodeList.size()> 1){
//           throw new JeecgBootException("存在多条记录，检查数据");
//       }
       if(CollectionUtil.isNotEmpty(reviewCodeList)){
           throw new JeecgBootException("已存在评审记录，请勿重复发起");
       }
        // 保存当前主表信息
       ReviewCode reviewCode = new ReviewCode().setXqName(reviewRecord.getXqName())
               .setKjxqNum(reviewRecord.getKjxqNum()).setIttaskNum(reviewRecord.getIttaskNum())
               .setXqNumber(reviewRecord.getXqNumber()).setSystemName(reviewRecord.getSystems());
        reviewCodeService.save(reviewCode);
        //保存当前评审记录模板内容
        List<CodeReviewChecklistRules> checklistRules = codeReviewChecklistRulesService.list();
        for (CodeReviewChecklistRules rules:checklistRules) {
            ReviewCodeChecklistResult result = new ReviewCodeChecklistResult().setRefId(reviewCode.getId()).setCheckGroup(rules.getCheckGroup())
                    .setCheckTitle(rules.getCheckTitle()).setSeqNo(rules.getSeqNo()).setCheckContent(rules.getCheckContent());
            reviewCodeChecklistResultService.save(result);
        }

        ReviewRecord record =  reviewRecordService.getById(reviewRecord.getId());
        record.setReviewCode("1");
        reviewRecordService.updateById(record);

        return reviewCode;
    }

    @Override
    @Transactional
    public ReviewPublish perCheckPublishReview(ReviewRecord reviewRecord) {
        if(StringUtils.isAnyEmpty(reviewRecord.getXqNumber(),reviewRecord.getKjxqNum(),reviewRecord.getIttaskNum())){
            throw new JeecgBootException("输入需求内容为空");
        }
        QueryWrapper<ReviewPublish> queryWrapper = new QueryWrapper<ReviewPublish>();
        queryWrapper.lambda().eq(ReviewPublish::getXqNumber,reviewRecord.getXqNumber()).eq(ReviewPublish::getKjxqNum,reviewRecord.getKjxqNum()).eq(ReviewPublish::getIttaskNum,reviewRecord.getIttaskNum());
        List<ReviewPublish> reviewPublishes = reviewPublishService.list(queryWrapper);
//       if(reviewCodeList != null && reviewCodeList.size()> 1){
//           throw new JeecgBootException("存在多条记录，检查数据");
//       }
        if(CollectionUtil.isNotEmpty(reviewPublishes)){
            throw new JeecgBootException("已存在评审记录，请勿重复发起");
        }
        // 保存当前主表信息
        ReviewPublish reviewPublish = new ReviewPublish().setXqName(reviewRecord.getXqName())
                .setKjxqNum(reviewRecord.getKjxqNum()).setIttaskNum(reviewRecord.getIttaskNum())
                .setXqNumber(reviewRecord.getXqNumber()).setSystems(reviewRecord.getSystems());
        reviewPublishService.save(reviewPublish);
        //保存当前评审记录模板内容
        List<PublishReviewChecklistRules> checklistRules = publishReviewChecklistRulesService.list();
        for (PublishReviewChecklistRules rules:checklistRules) {
            ReviewPublishChecklistResult result = new ReviewPublishChecklistResult().setRefId(reviewPublish.getId()).setCheckGroup(rules.getCheckGroup())
                    .setCheckTitle(rules.getCheckTitle()).setSeqNo(rules.getSeqNo()).setCheckContent(rules.getCheckContent());
            reviewPublishChecklistResultService.save(result);
        }

        ReviewRecord record =  reviewRecordService.getById(reviewRecord.getId());
        record.setReviewPublish("1");
        reviewRecordService.updateById(record);

        return reviewPublish;
    }
}