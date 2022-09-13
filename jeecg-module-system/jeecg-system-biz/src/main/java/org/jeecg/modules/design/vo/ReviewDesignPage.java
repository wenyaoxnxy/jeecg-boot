package org.jeecg.modules.design.vo;

import java.util.List;
import org.jeecg.modules.design.entity.ReviewDesign;
import org.jeecg.modules.design.entity.ReviewDesignDetail;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 概设评审记录
 * @Author: jeecg-boot
 * @Date:   2022-09-13
 * @Version: V1.0
 */
@Data
@ApiModel(value="kf_review_designPage对象", description="概设评审记录")
public class ReviewDesignPage {
	
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
	/**需求编号*/
  	@Excel(name = "需求编号", width = 15)
	private String xqNumber;
	/**需求名称*/
  	@Excel(name = "需求名称", width = 15)
	private String xqName;
	/**科技需求编号*/
  	@Excel(name = "科技需求编号", width = 15)
	private String kjxqNum;
	/**科技子任务编号*/
  	@Excel(name = "科技子任务编号", width = 15)
	private String ittaskNum;
	/**系统名称*/
  	@Excel(name = "系统名称", width = 15)
	private String systems;
	/**评审阶段*/
  	@Excel(name = "评审阶段", width = 15)
	private String reviewStep;
	/**评审人员*/
  	@Excel(name = "评审人员", width = 15)
	private String reviewMembers;
	
	@ExcelCollection(name="概设评审明细记录")
	@ApiModelProperty(value = "概设评审明细记录")
	private List<ReviewDesignDetail> reviewDesignDetailList;
	
}
