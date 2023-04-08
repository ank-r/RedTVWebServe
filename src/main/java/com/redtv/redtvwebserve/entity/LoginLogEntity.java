package com.redtv.redtvwebserve.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 登录日志
 * @author admin
 */
@Data
@TableName("login_log")
public class LoginLogEntity {
	/**
	 * id
	 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 登录时间
	 */
	private Long loginTime;

	/**
	 * 登录IP
	 */
	private String ip;

	/**
	 * 浏览器UA
	 */
	private String ua;

	/**
	 * 登录用户ID
	 */
	private Long userid;

}
