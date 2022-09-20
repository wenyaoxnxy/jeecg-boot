package org.jeecg.modules.review.entity;

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
 * @Description: 评审记录登记表
 * @Author: jeecg-boot
 * @Date:   2022-08-29
 * @Version: V1.0
 */
@Data
@TableName("kf_review_record")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="kf_review_record对象", description="评审记录登记表")
public class ReviewRecord {
    
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
	/**需求编号*/
	@Excel(name = "需求编号", width = 15)
    @ApiModelProperty(value = "需求编号")
	private String xqNumber;
	/**需求名称*/
	@Excel(name = "需求名称", width = 15)
    @ApiModelProperty(value = "需求名称")
	private String xqName;
	/**科技需求编号*/
	@Excel(name = "科技需求编号", width = 15)
    @ApiModelProperty(value = "科技需求编号")
	private String kjxqNum;
	/**科技子任务编号*/
	@Excel(name = "科技子任务编号", width = 15)
    @ApiModelProperty(value = "科技子任务编号")
	private String ittaskNum;
	/**系统名称*/
	@Excel(name = "系统名称", width = 15)
    @ApiModelProperty(value = "系统名称")
	private java.lang.String systems;
	/**概要设计评审*/
	@Excel(name = "概要设计评审", width = 15)
    @ApiModelProperty(value = "概要设计评审")
	private java.lang.String reviewDesign;
	/**代码评审*/
	@Excel(name = "代码评审", width = 15)
    @ApiModelProperty(value = "代码评审")
	private String reviewCode;
	/**上线评审*/
	@Excel(name = "上线评审", width = 15)
    @ApiModelProperty(value = "上线评审")
	private String reviewPublish;
	/**投产日期*/
	@Excel(name = "投产日期", width = 15)
	@ApiModelProperty(value = "投产日期")
	private String versionplan;
}
