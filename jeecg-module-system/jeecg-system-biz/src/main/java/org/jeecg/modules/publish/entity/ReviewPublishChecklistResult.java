package org.jeecg.modules.publish.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 上线评审检查清单检查结果
 * @Author: jeecg-boot
 * @Date:   2022-08-25
 * @Version: V1.0
 */
@Data
@TableName("kf_review_publish_checklist_result")
@ApiModel(value="kf_review_publish对象", description="上线评审记录")
@Accessors(chain = true)
public class ReviewPublishChecklistResult implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "主键")
	private java.lang.String id;
	/**创建人*/
    @Excel(name = "创建人", width = 15)
	@ApiModelProperty(value = "创建人")
	private java.lang.String createBy;
	/**创建日期*/
	@Excel(name = "创建日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "创建日期")
	private java.util.Date createTime;
	/**更新人*/
    @Excel(name = "更新人", width = 15)
	@ApiModelProperty(value = "更新人")
	private java.lang.String updateBy;
	/**更新日期*/
	@Excel(name = "更新日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "更新日期")
	private java.util.Date updateTime;
	/**关联ID*/
	@ApiModelProperty(value = "关联ID")
	private java.lang.String refId;
	/**检查分组*/
    @Excel(name = "检查分组", width = 15)
	@ApiModelProperty(value = "检查分组")
	private java.lang.String checkGroup;
	/**序号*/
    @Excel(name = "序号", width = 15)
	@ApiModelProperty(value = "序号")
	private java.lang.String seqNo;
	/**确认项目*/
    @Excel(name = "确认项目", width = 15)
	@ApiModelProperty(value = "确认项目")
	private java.lang.String checkTitle;
	/**检查内容*/
    @Excel(name = "检查内容", width = 15)
	@ApiModelProperty(value = "检查内容")
	private java.lang.String checkContent;
	/**检查结果*/
    @Excel(name = "检查结果", width = 15)
	@ApiModelProperty(value = "检查结果")
	private java.lang.String problemStatus;
	/**备注及详细说明*/
    @Excel(name = "备注及详细说明", width = 15)
	@ApiModelProperty(value = "备注及详细说明")
	private java.lang.String note;
}
