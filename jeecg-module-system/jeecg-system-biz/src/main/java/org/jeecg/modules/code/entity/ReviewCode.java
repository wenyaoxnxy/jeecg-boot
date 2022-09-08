package org.jeecg.modules.code.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 代码评审记录
 * @Author: jeecg-boot
 * @Date:   2022-08-25
 * @Version: V1.0
 */
@Data
@TableName("kf_review_code")
@ApiModel(value="kf_review_code对象", description="代码评审记录")
@Accessors(chain = true)
public class ReviewCode implements Serializable {
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
	private String systemName;
	/**评审阶段*/
	@ApiModelProperty(value = "评审阶段")
	private String reviewStep;
	/**评审人员*/
	@ApiModelProperty(value = "评审人员")
	private String reviewMembers;
	/**findbugs报告记录*/
	@ApiModelProperty(value = "findbugs报告记录")
	private String findbugsLog;
	/**sonar报告*/
	@ApiModelProperty(value = "sonar报告")
	private String sonarLog;
}
