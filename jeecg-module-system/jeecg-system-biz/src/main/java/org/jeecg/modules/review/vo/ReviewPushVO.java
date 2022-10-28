package org.jeecg.modules.review.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 评审VO
 * @author: jeecg-boot
 */
@Data
public class ReviewPushVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**systems*/
	private String systems;
	/**xq_names*/
	private String xqnames;
	public ReviewPushVO(String systems, String xqnames) {
		super();
		this.systems = systems;
		this.xqnames = xqnames;
	}

	public ReviewPushVO(){

	}

}
