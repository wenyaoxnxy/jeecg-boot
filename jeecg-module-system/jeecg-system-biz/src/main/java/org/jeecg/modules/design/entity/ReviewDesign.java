package org.jeecg.modules.design.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 概设评审记录
 * @Author: jeecg-boot
 * @Date:   2022-09-13
 * @Version: V1.0
 */
@Data
@TableName("kf_review_design")
@ApiModel(value="kf_review_design对象", description="概设评审记录")
@Accessors(chain = true)
public class ReviewDesign implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "主键")
	private String id;
	/**创建人*/
	@ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "创建日期")
	private Date createTime;
	/**更新人*/
	@ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "更新日期")
	private Date updateTime;
	/**需求编号*/
	@ApiModelProperty(value = "需求编号")
	private String xqNumber;
	/**需求名称*/
	@ApiModelProperty(value = "需求名称")
	private String xqName;
	/**科技需求编号*/
	@ApiModelProperty(value = "科技需求编号")
	private String kjxqNum;
	/**科技子任务编号*/
	@ApiModelProperty(value = "科技子任务编号")
	private String ittaskNum;
	/**系统名称*/
	@ApiModelProperty(value = "系统名称")
	private String systems;
	/**评审阶段*/
	@ApiModelProperty(value = "评审阶段")
	private String reviewStep;
	/**评审人员*/
	@ApiModelProperty(value = "评审人员")
	private String reviewMembers;
}
