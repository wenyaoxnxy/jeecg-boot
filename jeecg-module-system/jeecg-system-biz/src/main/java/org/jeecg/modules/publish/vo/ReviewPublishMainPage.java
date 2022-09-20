package org.jeecg.modules.publish.vo;

import java.util.List;
import org.jeecg.modules.publish.entity.ReviewPublishMain;
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
 * @Description: 上线评审系统记录
 * @Author: jeecg-boot
 * @Date:   2022-09-20
 * @Version: V1.0
 */
@Data
@ApiModel(value="kf_review_publish_mainPage对象", description="上线评审系统记录")
public class ReviewPublishMainPage {
	
	/**主键*/
	private String id;
	/**创建人*/
  	@Excel(name = "创建人", width = 15)
	private String createBy;
	/**创建日期*/
  	@Excel(name = "创建日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**更新人*/
  	@Excel(name = "更新人", width = 15)
	private String updateBy;
	/**更新日期*/
  	@Excel(name = "更新日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	/**系统名称*/
  	@Excel(name = "系统名称", width = 15)
	private String systems;
	/**上线日期*/
  	@Excel(name = "上线日期", width = 15)
	private String versionplan;
	/**评审阶段*/
  	@Excel(name = "评审阶段", width = 15)
	private String reviewStep;
	/**评审人员*/
  	@Excel(name = "评审人员", width = 15)
	private String reviewMembers;
	
	@ExcelCollection(name="上线评审需求明细记录")
	@ApiModelProperty(value = "上线评审需求明细记录")
	private List<ReviewPublish> reviewPublishList;
	@ExcelCollection(name="上线评审问题记录")
	@ApiModelProperty(value = "上线评审问题记录")
	private List<ReviewPublishDetail> reviewPublishDetailList;
	@ExcelCollection(name="上线评审检查清单记录列表")
	@ApiModelProperty(value = "上线评审检查清单记录列表")
	private List<ReviewPublishChecklistResult> reviewPublishChecklistResultList;
	
}
