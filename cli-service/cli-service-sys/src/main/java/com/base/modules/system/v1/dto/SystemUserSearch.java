package com.base.modules.system.v1.dto;

import lombok.Data;

/**
 * 系统用户列表查询数据传输模型
 * @author hzh 2018/8/18 下午4:05
 */
@Data
public class SystemUserSearch {

	private String keyword;
	private String roleId;
}
