package org.jeecg.modules.publish.entity;

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
 * @Description: 上线评审明细记录
 * @Author: jeecg-boot
 * @Date:   2022-08-25
 * @Version: V1.0
 */
@Data
@TableName("kf_review_publish_detail")
@ApiModel(value="kf_review_publish对象", description="上线评审记录")
public class ReviewPublishDetail implements Serializable {
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
	/**问题类型*/
    @Excel(name = "问题类型", width = 15)
	@ApiModelProperty(value = "问题类型")
	private java.lang.String problemType;
	/**问题描述*/
    @Excel(name = "问题描述", width = 15)
	@ApiModelProperty(value = "问题描述")
	private java.lang.String problemDescription;
	/**对应措施*/
    @Excel(name = "对应措施", width = 15)
	@ApiModelProperty(value = "对应措施")
	private java.lang.String problemMeasure;
	/**责任人*/
    @Excel(name = "责任人", width = 15)
	@ApiModelProperty(value = "责任人")
	private java.lang.String problemResponsible;
	/**问题状态*/
    @Excel(name = "问题状态", width = 15)
	@ApiModelProperty(value = "问题状态")
	private java.lang.String problemStatus;
	/**处理日期*/
	@Excel(name = "处理日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "处理日期")
	private java.util.Date problemFixTime;
	/**备注*/
    @Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
	private java.lang.String note;
}
