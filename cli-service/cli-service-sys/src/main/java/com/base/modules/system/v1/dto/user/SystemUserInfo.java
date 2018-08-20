package com.base.modules.system.v1.dto.user;

import lombok.Data;

/**
 * 系统管理员信息数据传输模型
 * @author hzh 2018/8/18 下午3:49
 */
@Data
public class SystemUserInfo {

	private String id;
	private String avatarUrl;
	private String account;
	private String nickName;
	private String roleId;
	private String roleName;
	private String createTime;
	private String modifyTime;
	private Integer state;
}
