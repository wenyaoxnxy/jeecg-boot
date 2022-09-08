package org.jeecg.modules.code.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 代码评审明细记录
 * @Author: jeecg-boot
 * @Date:   2022-08-25
 * @Version: V1.0
 */
@Data
@TableName("kf_review_code_detail")
@ApiModel(value="kf_review_code对象", description="代码评审记录")
public class ReviewCodeDetail implements Serializable {
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
	/**缺陷位置*/
    @Excel(name = "缺陷位置", width = 15)
	@ApiModelProperty(value = "缺陷位置")
	private String bugLocation;
	/**缺陷描述*/
    @Excel(name = "缺陷描述", width = 15)
	@ApiModelProperty(value = "缺陷描述")
	private String bugDescription;
	/**对应措施*/
    @Excel(name = "对应措施", width = 15)
	@ApiModelProperty(value = "对应措施")
	private String bugMeasure;
	/**责任人*/
    @Excel(name = "责任人", width = 15)
	@ApiModelProperty(value = "责任人")
	private String bugResponsible;
	/**修复时间*/
	@Excel(name = "修复时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "修复时间")
	private Date bugFixTime;
	/**备注*/
    @Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
	private String note;
}
