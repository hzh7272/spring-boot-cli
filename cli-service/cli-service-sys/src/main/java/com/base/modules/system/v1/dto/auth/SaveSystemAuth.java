package com.base.modules.system.v1.dto.auth;

import lombok.Data;

/**
 * 保存系统权限数据模型
 * @author hzh 2018/8/20 下午11:40
 */
@Data
public class SaveSystemAuth {

	private String id;
	private String parentId;
	private String species;
	private Integer type;
	private String name;
	private String resource;
	private String icon;
	private String permission;
	private Integer sort;
	private Integer state;
}
