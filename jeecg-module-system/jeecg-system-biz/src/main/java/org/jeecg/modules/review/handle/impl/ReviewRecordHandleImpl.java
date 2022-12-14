package org.jeecg.modules.review.handle.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.jaemon.dinger.DingerSender;
import com.github.jaemon.dinger.core.entity.DingerRequest;
import com.github.jaemon.dinger.core.entity.enums.MessageSubType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.modules.code.entity.ReviewCode;
import org.jeecg.modules.code.entity.ReviewCodeChecklistResult;
import org.jeecg.modules.code.service.IReviewCodeChecklistResultService;
import org.jeecg.modules.code.service.IReviewCodeDetailService;
import org.jeecg.modules.code.service.IReviewCodeService;
import org.jeecg.modules.design.entity.ReviewDesign;
import org.jeecg.modules.design.service.IReviewDesignService;
import org.jeecg.modules.publish.entity.ReviewPublish;
import org.jeecg.modules.publish.entity.ReviewPublishChecklistResult;
import org.jeecg.modules.publish.entity.ReviewPublishMain;
import org.jeecg.modules.publish.service.IReviewPublishChecklistResultService;
import org.jeecg.modules.publish.service.IReviewPublishDetailService;
import org.jeecg.modules.publish.service.IReviewPublishMainService;
import org.jeecg.modules.publish.service.IReviewPublishService;
import org.jeecg.modules.review.entity.ReviewRecord;
import org.jeecg.modules.review.handle.IReviewRecordHandle;
import org.jeecg.modules.review.service.IReviewRecordService;
import org.jeecg.modules.review.vo.ReviewPushVO;
import org.jeecg.modules.rules.entity.CodeReviewChecklistRules;
import org.jeecg.modules.rules.entity.PublishReviewChecklistRules;
import org.jeecg.modules.rules.service.ICodeReviewChecklistRulesService;
import org.jeecg.modules.rules.service.IPublishReviewChecklistRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Description: ?????????????????????
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
    private IReviewPublishMainService reviewPublishMainService;

    @Autowired
    private IReviewPublishService reviewPublishService;

    @Autowired
    private IReviewPublishDetailService reviewPublishDetailService;

    @Autowired
    private IReviewPublishChecklistResultService reviewPublishChecklistResultService;

    @Autowired
    private IPublishReviewChecklistRulesService publishReviewChecklistRulesService;

    @Autowired
    private IReviewDesignService reviewDesignService;

    @Autowired
    private DingerSender dingerSender;

    @Override
    @Transactional
    public ReviewCode perCheckCodeReview(ReviewRecord reviewRecord) {
       if(StringUtils.isAnyEmpty(reviewRecord.getXqNumber(),reviewRecord.getKjxqNum(),reviewRecord.getIttaskNum())){
            throw new JeecgBootException("????????????????????????");
       }
       QueryWrapper<ReviewCode> queryWrapper = new QueryWrapper<ReviewCode>();
       queryWrapper.lambda().eq(ReviewCode::getXqNumber,reviewRecord.getXqNumber()).eq(ReviewCode::getKjxqNum,reviewRecord.getKjxqNum()).eq(ReviewCode::getIttaskNum,reviewRecord.getIttaskNum());
       List<ReviewCode> reviewCodeList =  reviewCodeService.list(queryWrapper);
//       if(reviewCodeList != null && reviewCodeList.size()> 1){
//           throw new JeecgBootException("?????????????????????????????????");
//       }
       if(CollectionUtil.isNotEmpty(reviewCodeList)){
           throw new JeecgBootException("??????????????????????????????????????????");
       }
        // ????????????????????????
       ReviewCode reviewCode = new ReviewCode().setXqName(reviewRecord.getXqName())
               .setKjxqNum(reviewRecord.getKjxqNum()).setIttaskNum(reviewRecord.getIttaskNum())
               .setXqNumber(reviewRecord.getXqNumber()).setSystemName(reviewRecord.getSystems());
        reviewCodeService.save(reviewCode);
        //????????????????????????????????????
        QueryWrapper<CodeReviewChecklistRules> queryWrapperRules = new QueryWrapper<CodeReviewChecklistRules>();
        queryWrapperRules.lambda().like(CodeReviewChecklistRules::getNote,reviewRecord.getSystems());
        List<CodeReviewChecklistRules> checklistRules = codeReviewChecklistRulesService.list(queryWrapperRules);
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
    public ReviewPublishMain perCheckPublishReview(ReviewRecord reviewRecord) {
        if(StringUtils.isAnyEmpty(reviewRecord.getSystems(),reviewRecord.getVersionplan())){
            throw new JeecgBootException("????????????????????????????????????");
        }
        QueryWrapper<ReviewPublishMain> queryWrapper = new QueryWrapper<ReviewPublishMain>();
        queryWrapper.lambda().eq(ReviewPublishMain::getSystems,reviewRecord.getSystems()).eq(ReviewPublishMain::getVersionplan,reviewRecord.getVersionplan());
        List<ReviewPublishMain> reviewPublishes = reviewPublishMainService.list(queryWrapper);
//       if(reviewCodeList != null && reviewCodeList.size()> 1){
//           throw new JeecgBootException("?????????????????????????????????");
//       }
        if(CollectionUtil.isNotEmpty(reviewPublishes)){
            throw new JeecgBootException("????????????????????????????????????????????????????????????????????????");
        }
        // ????????????????????????
        ReviewPublishMain reviewPublishMain = new ReviewPublishMain().setSystems(reviewRecord.getSystems())
                .setVersionplan(reviewRecord.getVersionplan());
        reviewPublishMainService.save(reviewPublishMain);
        //???????????????????????????????????????
        QueryWrapper<ReviewRecord> queryRecordWrapper = new QueryWrapper<ReviewRecord>();
        queryRecordWrapper.lambda().eq(ReviewRecord::getSystems,reviewRecord.getSystems()).eq(ReviewRecord::getVersionplan,reviewRecord.getVersionplan());
        List<ReviewRecord> reviewRecordList = reviewRecordService.list(queryRecordWrapper);
        for (ReviewRecord record:reviewRecordList) {
            ReviewPublish publish = new ReviewPublish().setRefId(reviewPublishMain.getId())
                    .setSystems(reviewPublishMain.getSystems()).setXqNumber(record.getXqNumber())
                    .setXqName(record.getXqName()).setKjxqNum(record.getKjxqNum()).setIttaskNum(record.getIttaskNum());
            reviewPublishService.save(publish);
        }
        //????????????????????????????????????
        QueryWrapper<PublishReviewChecklistRules> queryWrapperRules = new QueryWrapper<PublishReviewChecklistRules>();
        queryWrapperRules.lambda().like(PublishReviewChecklistRules::getNote,reviewRecord.getSystems());
        List<PublishReviewChecklistRules> checklistRules = publishReviewChecklistRulesService.list(queryWrapperRules);
        for (PublishReviewChecklistRules rules:checklistRules) {
            ReviewPublishChecklistResult result = new ReviewPublishChecklistResult().setRefId(reviewPublishMain.getId()).setCheckGroup(rules.getCheckGroup())
                    .setCheckTitle(rules.getCheckTitle()).setSeqNo(rules.getSeqNo()).setCheckContent(rules.getCheckContent());
            reviewPublishChecklistResultService.save(result);
        }

        ReviewRecord record =  reviewRecordService.getById(reviewRecord.getId());
        record.setReviewPublish("1");
        reviewRecordService.updateById(record);

        return reviewPublishMain;
    }

    @Override
    public ReviewDesign perDesignReview(ReviewRecord reviewRecord) {
        if(StringUtils.isAnyEmpty(reviewRecord.getXqNumber(),reviewRecord.getKjxqNum(),reviewRecord.getIttaskNum())){
            throw new JeecgBootException("????????????????????????");
        }
        QueryWrapper<ReviewDesign> queryWrapper = new QueryWrapper<ReviewDesign>();
        queryWrapper.lambda().eq(ReviewDesign::getXqNumber,reviewRecord.getXqNumber()).eq(ReviewDesign::getKjxqNum,reviewRecord.getKjxqNum()).eq(ReviewDesign::getIttaskNum,reviewRecord.getIttaskNum());
        List<ReviewDesign> reviewDesigns = reviewDesignService.list(queryWrapper);
//       if(reviewCodeList != null && reviewCodeList.size()> 1){
//           throw new JeecgBootException("?????????????????????????????????");
//       }
        if(CollectionUtil.isNotEmpty(reviewDesigns)){
            throw new JeecgBootException("??????????????????????????????????????????");
        }
        // ????????????????????????
        ReviewDesign reviewDesign = new ReviewDesign().setXqName(reviewRecord.getXqName())
                .setKjxqNum(reviewRecord.getKjxqNum()).setIttaskNum(reviewRecord.getIttaskNum())
                .setXqNumber(reviewRecord.getXqNumber()).setSystems(reviewRecord.getSystems());
        reviewDesignService.save(reviewDesign);

        ReviewRecord record =  reviewRecordService.getById(reviewRecord.getId());
        record.setReviewDesign("1");
        reviewRecordService.updateById(record);
        return reviewDesign;
    }

    @Override
    public void reviewInfoPush(String versionPlan) {

        if(StringUtils.isBlank(versionPlan)){
            throw new JeecgBootException("????????????????????????");
        }
        // ??????????????????
        List<ReviewPushVO> pushList = reviewRecordService.selectNeedReviewCode(versionPlan);

        StringBuffer pushText = new StringBuffer();
        if(CollectionUtils.isEmpty(pushList)){
           pushText.append("?????????").append(versionPlan).append("??????????????????????????????????????????");
        }else {
            pushText.append("?????????????????????\n");
            for (ReviewPushVO reviewPush:pushList) {
                pushText.append("???").append(reviewPush.getSystems()).append("???:").append(reviewPush.getXqnames()).append("\n");
            }
        }
        dingerSender.send(MessageSubType.TEXT,
                DingerRequest.request(pushText.toString())
        );

        // ??????????????????
        List<ReviewPushVO> pushPublishList = reviewRecordService.selectNeedReviewPublish(versionPlan);

        StringBuffer pushPublishText = new StringBuffer();
        if(CollectionUtils.isEmpty(pushPublishList)){
            pushPublishText.append("?????????").append(versionPlan).append("??????????????????????????????????????????");
        }else {
            pushPublishText.append("?????????????????????\n");
            for (ReviewPushVO reviewPush:pushPublishList) {
                pushPublishText.append("???").append(reviewPush.getSystems()).append("???:").append(reviewPush.getXqnames()).append("\n");
            }
        }
        dingerSender.send(MessageSubType.TEXT,
                DingerRequest.request(pushPublishText.toString())
        );

    }
}
