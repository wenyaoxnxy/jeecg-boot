package org.jeecg.modules.code.vo;

import java.util.List;
import org.jeecg.modules.code.entity.ReviewCode;
import org.jeecg.modules.code.entity.ReviewCodeDetail;
import org.jeecg.modules.code.entity.ReviewCodeChecklistResult;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 代码评审记录
 * @Author: jeecg-boot
 * @Date:   2022-08-25
 * @Version: V1.0
 */
@Data
@ApiModel(value="kf_review_codePage对象", description="代码评审记录")
public class ReviewCodePage {
	
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
	private String systemName;
	/**评审阶段*/
  	@Excel(name = "评审阶段", width = 15)
	private String reviewStep;
	/**评审人员*/
  	@Excel(name = "评审人员", width = 15)
	private String reviewMembers;
	/**findbugs报告记录*/
  	@Excel(name = "findbugs报告记录", width = 15)
	private String findbugsLog;
	/**sonar报告*/
  	@Excel(name = "sonar报告", width = 15)
	private String sonarLog;
	
	@ExcelCollection(name="代码评审明细记录")
	@ApiModelProperty(value = "代码评审明细记录")
	private List<ReviewCodeDetail> reviewCodeDetailList;
	@ExcelCollection(name="代码评审检查清单检查结果")
	@ApiModelProperty(value = "代码评审检查清单检查结果")
	private List<ReviewCodeChecklistResult> reviewCodeChecklistResultList;
	
}
