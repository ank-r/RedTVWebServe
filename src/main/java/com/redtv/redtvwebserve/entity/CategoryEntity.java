package com.redtv.redtvwebserve.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 *
 * @author admin
 */
@Data
@TableName("category")
public class CategoryEntity {
	@TableId(type = IdType.AUTO)
	private Integer id;

	/**
	 * 分区名
	 */
	private String name;

	/**
	 * 分区级别【1 一级分区， 2 二级分区】
	 */
	private Integer type;

	/**
	 * 父级分区
	 */
	private Integer fatherId;

	/**
	 * 介绍
	 */
	private String describes;

	/**
	 * 图标
	 */
	private String icon;

	/**
	 * 排序
	 */
	private Integer sort;

	/**
	 * 
	 */
	private Long createTime;

	/**
	 * 
	 */
	private Long updateTime;

	/**
	 * 用户查询子分类
	 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@TableField(exist = false)
	private List<CategoryEntity> children;

}
