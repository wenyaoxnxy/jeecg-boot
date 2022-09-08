package org.jeecg.modules.rules.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 上线检查规则列表
 * @Author: jeecg-boot
 * @Date:   2022-08-26
 * @Version: V1.0
 */
@Data
@TableName("kf_publish_review_checklist_rules")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="kf_publish_review_checklist_rules对象", description="上线检查规则列表")
public class PublishReviewChecklistRules {
    
	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
	private String id;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建日期*/
	@Excel(name = "创建日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建日期")
	private Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新日期*/
	@Excel(name = "更新日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新日期")
	private Date updateTime;
	/**检查分组*/
	@Excel(name = "检查分组", width = 15)
    @ApiModelProperty(value = "检查分组")
	private String checkGroup;
	/**序号*/
	@Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
	private String seqNo;
	/**确认项目*/
	@Excel(name = "确认项目", width = 15)
    @ApiModelProperty(value = "确认项目")
	private String checkTitle;
	/**检查内容*/
	@Excel(name = "检查内容", width = 15)
    @ApiModelProperty(value = "检查内容")
	private String checkContent;
	/**检查结果*/
	@Excel(name = "检查结果", width = 15)
    @ApiModelProperty(value = "检查结果")
	private String problemStatus;
	/**备注及详细说明*/
	@Excel(name = "备注及详细说明", width = 15)
    @ApiModelProperty(value = "备注及详细说明")
	private String note;
}
