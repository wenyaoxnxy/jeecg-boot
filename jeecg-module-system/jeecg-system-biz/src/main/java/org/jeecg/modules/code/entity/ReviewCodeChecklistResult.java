package org.jeecg.modules.code.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 代码评审检查清单检查结果
 * @Author: jeecg-boot
 * @Date:   2022-08-25
 * @Version: V1.0
 */
@Data
@TableName("kf_review_code_checklist_result")
@ApiModel(value="kf_review_code对象", description="代码评审记录")
@Accessors(chain = true)
public class ReviewCodeChecklistResult implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "主键")
	private String id;
	/**创建人*/
    @Excel(name = "创建人", width = 15)
	@ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建日期*/
	@Excel(name = "创建日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "创建日期")
	private Date createTime;
	/**更新人*/
    @Excel(name = "更新人", width = 15)
	@ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新日期*/
	@Excel(name = "更新日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "更新日期")
	private Date updateTime;
	/**关联ID*/
	@ApiModelProperty(value = "关联ID")
	private String refId;
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
