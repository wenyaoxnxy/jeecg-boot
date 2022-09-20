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
 * @Description: 上线评审需求明细记录
 * @Author: jeecg-boot
 * @Date:   2022-09-20
 * @Version: V1.0
 */
@Data
@TableName("kf_review_publish")
@Accessors(chain = true)
@ApiModel(value="kf_review_publish_main对象", description="上线评审系统记录")
public class ReviewPublish implements Serializable {
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
	private String systems;
	/**评审阶段*/
    @Excel(name = "评审阶段", width = 15)
	@ApiModelProperty(value = "评审阶段")
	private String reviewStep;
	/**评审人员*/
    @Excel(name = "评审人员", width = 15)
	@ApiModelProperty(value = "评审人员")
	private String reviewMembers;
	/**上线日期*/
    @Excel(name = "上线日期", width = 15)
	@ApiModelProperty(value = "上线日期")
	private String versionplan;
}
