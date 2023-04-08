package com.redtv.redtvwebserve.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author admin
 */
@Data
@TableName("user")
public class UserEntity {

	/**
	 * 用户ID
	 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 用户名
	 */
	@NotBlank(message = "用户名不能为空")
	private String username;

	/**
	 * 邮箱
	 */
	@Email(message = "邮箱格式不正确")
	@NotBlank(message = "邮箱不能为空")
	private String mail;

	/**
	 * 密码,加密后
	 */
	@NotBlank(message = "密码不能为空")
	private String password;

	/**
	 * 手机号
	 */
	private String phone;

	/**
	 * 创建时间
	 */
	private Long createTime;

	/**
	 * 提交视频，图片，文章数
	 */
	private Long submitCount;

	/**
	 * 关注数
	 */
	private Long followCount;

	/**
	 * 粉丝数
	 */
	private Long fansCount;

	/**
	 * 头像
	 */
	private String avatarUrl;

	/**
	 * 是否是管理员
	 */
	private Integer isAdmin;

	/**
	 * 简介
	 * */
	private String introduction;

	/**
	 * 首页大图url
	 */
	private String topImgUrl;


}
