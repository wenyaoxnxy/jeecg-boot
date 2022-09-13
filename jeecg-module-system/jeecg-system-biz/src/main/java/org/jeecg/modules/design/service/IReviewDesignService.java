package org.jeecg.modules.design.service;

import org.jeecg.modules.design.entity.ReviewDesignDetail;
import org.jeecg.modules.design.entity.ReviewDesign;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 概设评审记录
 * @Author: jeecg-boot
 * @Date:   2022-09-13
 * @Version: V1.0
 */
public interface IReviewDesignService extends IService<ReviewDesign> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(ReviewDesign reviewDesign,List<ReviewDesignDetail> reviewDesignDetailList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(ReviewDesign reviewDesign,List<ReviewDesignDetail> reviewDesignDetailList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
