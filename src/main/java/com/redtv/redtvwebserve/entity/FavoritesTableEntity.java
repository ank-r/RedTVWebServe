package com.redtv.redtvwebserve.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 收藏夹， 点赞的视频
 * @author admin
 */
@Data
@TableName("favorites_table")
public class FavoritesTableEntity {
	/**
	 * 
	 */
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
	 * 
	 */
	private Long createTime;

}
