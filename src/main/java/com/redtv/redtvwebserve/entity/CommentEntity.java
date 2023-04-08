package com.redtv.redtvwebserve.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 *评论表
 * @author admin
 */
@Data
@TableName("comment")
public class CommentEntity {
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 
	 */
	private Long articleId;

	/**
	 * 
	 */
	private Long userId;

	/**
	 * 评论
	 */
	private String comment;


	/**
	 * [0 正常  1 删除]
	 */
	private Integer status;

	/**
	 * 
	 */
	private Long createTime;

	/**
	 * 
	 */
	private Long updateTime;



	public void initComment() {
		long time = System.currentTimeMillis();
		this.status = 0;
		this.createTime = time;
		this.updateTime = time;

	}
}
