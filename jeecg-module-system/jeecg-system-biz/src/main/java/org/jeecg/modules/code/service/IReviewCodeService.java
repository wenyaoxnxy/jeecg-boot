package org.jeecg.modules.code.service;

import org.jeecg.modules.code.entity.ReviewCodeDetail;
import org.jeecg.modules.code.entity.ReviewCodeChecklistResult;
import org.jeecg.modules.code.entity.ReviewCode;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 代码评审记录
 * @Author: jeecg-boot
 * @Date:   2022-08-25
 * @Version: V1.0
 */
public interface IReviewCodeService extends IService<ReviewCode> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(ReviewCode reviewCode,List<ReviewCodeDetail> reviewCodeDetailList,List<ReviewCodeChecklistResult> reviewCodeChecklistResultList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(ReviewCode reviewCode,List<ReviewCodeDetail> reviewCodeDetailList,List<ReviewCodeChecklistResult> reviewCodeChecklistResultList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
