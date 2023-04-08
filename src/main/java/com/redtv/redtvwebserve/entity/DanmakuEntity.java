package com.redtv.redtvwebserve.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 弹幕表
 * @author admin
 */
@Data
@TableName("danmaku")
public class DanmakuEntity {
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 视频ID
	 */
	private Long videoId;

	/**
	 * 弹幕颜色
	 */
	private String color;

	/**
	 * 弹幕发布人id
	 */
	private Long author;

	/**
	 * 弹幕内容
	 */
	private String text;

	/**
	 * 时间
	 */
	private Double time;

	/**
	 * 类型
	 */
	private Integer type;


	/**
	 * 十进制颜色数据
	 * */
	private Long colorDec;



}
