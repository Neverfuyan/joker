
package com.wj.springcloud.common.entity;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity<T> implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 实体编号（唯一标识）
	 */
	protected String id;
	/**
	 * 当前实体分页对象
	 */
	protected Page<T> page;

	/**
	 * 创建用户id
	 */
	protected String createUserId;

	/**
	 * 创建用户姓名
	 */
	protected String createUserName;


	/**
	 * 记录创建时间
	 * 年月日采用该注解
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date createTime = new Date();


	/**
	 * 更新用户id
	 */
	protected String updateUserId;

	/**
	 * 更新用户姓名
	 */
	protected String updateUserName;
	/**
	 * 记录更新时间
	 * 年月日采用该注解
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date updateTime = new Date();


	/**
	 * 备注
	 */
	protected String remarks;
}
