package com.base.modules.system.v1.dto.user;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

/**
 * 保存系统管理员数据传输模型
 * @author hzh 2018/8/18 下午3:45
 */
@Data
public class SaveSystemUser {

	private Long id;
	private String avatarUrl;
	@NotBlank(message = "请输入系统用户账号")
	@Length(min = 6, max = 16, message = "系统用户账号长度需大于等于6位，小于等于16位")
	private String account;
	@NotBlank(message = "请输入系统用户昵称")
	private String nickName;
	@NotBlank(message = "请选择系统用户角色")
	private String roleId;
	@NotBlank(message = "请输入系统用户初始密码")
	@Length(min = 6, max = 32, message = "系统用户密码长度需大于等于6位，小于等于32位")
	private String password;
	@Range(min = 1, max = 2, message = "系统用户状态无效")
	private Integer state;
}
