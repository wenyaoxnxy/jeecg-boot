package org.jeecg.modules.publish.service;

import org.jeecg.modules.publish.entity.ReviewPublishDetail;
import org.jeecg.modules.publish.entity.ReviewPublishChecklistResult;
import org.jeecg.modules.publish.entity.ReviewPublish;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 上线评审记录
 * @Author: jeecg-boot
 * @Date:   2022-08-25
 * @Version: V1.0
 */
public interface IReviewPublishService extends IService<ReviewPublish> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(ReviewPublish reviewPublish,List<ReviewPublishDetail> reviewPublishDetailList,List<ReviewPublishChecklistResult> reviewPublishChecklistResultList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(ReviewPublish reviewPublish,List<ReviewPublishDetail> reviewPublishDetailList,List<ReviewPublishChecklistResult> reviewPublishChecklistResultList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
