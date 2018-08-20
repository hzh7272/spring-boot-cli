package com.base.modules.system.v1.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 系统权限模型
 * @author hzh 2018/8/20 下午11:31
 */
@Data
@Document(collection = "systemAuth")
public class SystemAuth {

	@Id
	private String id;
	private String parentId;
	private String species;
	private Integer type;
	private String name;
	private String resource;
	private String icon;
	private String permission;
	private Integer sort;
	private Date createTime;
	@DBRef
	private SystemUser createUser;
	private Date modifyTime;
	@DBRef
	private SystemUser modifyUser;
	private Integer state;
}
