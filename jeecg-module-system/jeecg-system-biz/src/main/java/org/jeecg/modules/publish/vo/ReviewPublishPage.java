package org.jeecg.modules.publish.vo;

import java.util.List;
import org.jeecg.modules.publish.entity.ReviewPublish;
import org.jeecg.modules.publish.entity.ReviewPublishDetail;
import org.jeecg.modules.publish.entity.ReviewPublishChecklistResult;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 上线评审记录
 * @Author: jeecg-boot
 * @Date:   2022-08-25
 * @Version: V1.0
 */
@Data
@ApiModel(value="kf_review_publishPage对象", description="上线评审记录")
public class ReviewPublishPage {
	
	/**主键*/
	private java.lang.String id;
	/**创建人*/
  	@Excel(name = "创建人", width = 15)
	private java.lang.String createBy;
	/**创建日期*/
  	@Excel(name = "创建日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	/**更新人*/
  	@Excel(name = "更新人", width = 15)
	private java.lang.String updateBy;
	/**更新日期*/
  	@Excel(name = "更新日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;
	/**需求编号*/
  	@Excel(name = "需求编号", width = 15)
	private java.lang.String xqNumber;
	/**需求名称*/
  	@Excel(name = "需求名称", width = 15)
	private java.lang.String xqName;
	/**科技需求编号*/
  	@Excel(name = "科技需求编号", width = 15)
	private java.lang.String kjxqNum;
	/**科技子任务编号*/
  	@Excel(name = "科技子任务编号", width = 15)
	private java.lang.String ittaskNum;
	/**系统名称*/
  	@Excel(name = "系统名称", width = 15)
	private java.lang.String system;
	/**评审阶段*/
  	@Excel(name = "评审阶段", width = 15)
	private java.lang.String reviewStep;
	/**评审人员*/
  	@Excel(name = "评审人员", width = 15)
	private java.lang.String reviewMembers;
	
	@ExcelCollection(name="上线评审明细记录")
	@ApiModelProperty(value = "上线评审明细记录")
	private List<ReviewPublishDetail> reviewPublishDetailList;
	@ExcelCollection(name="上线评审检查清单检查结果")
	@ApiModelProperty(value = "上线评审检查清单检查结果")
	private List<ReviewPublishChecklistResult> reviewPublishChecklistResultList;
	
}
