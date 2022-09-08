package org.jeecg.modules.publish.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @Description: 上线评审记录
 * @Author: jeecg-boot
 * @Date:   2022-08-25
 * @Version: V1.0
 */
@Data
@TableName("kf_review_publish")
@ApiModel(value="kf_review_publish对象", description="上线评审记录")
@Accessors(chain = true)
public class ReviewPublish implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "主键")
	private java.lang.String id;
	/**创建人*/
	@ApiModelProperty(value = "创建人")
	private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "创建日期")
	private java.util.Date createTime;
	/**更新人*/
	@ApiModelProperty(value = "更新人")
	private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "更新日期")
	private java.util.Date updateTime;
	/**需求编号*/
	@ApiModelProperty(value = "需求编号")
	private java.lang.String xqNumber;
	/**需求名称*/
	@ApiModelProperty(value = "需求名称")
	private java.lang.String xqName;
	/**科技需求编号*/
	@ApiModelProperty(value = "科技需求编号")
	private java.lang.String kjxqNum;
	/**科技子任务编号*/
	@ApiModelProperty(value = "科技子任务编号")
	private java.lang.String ittaskNum;
	/**系统名称*/
    @ApiModelProperty(value = "系统名称")
	private java.lang.String systems;
	/**评审阶段*/
	@ApiModelProperty(value = "评审阶段")
	private java.lang.String reviewStep;
	/**评审人员*/
	@ApiModelProperty(value = "评审人员")
	private java.lang.String reviewMembers;
}
