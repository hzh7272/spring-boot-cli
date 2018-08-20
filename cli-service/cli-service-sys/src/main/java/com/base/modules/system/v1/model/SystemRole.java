package com.base.modules.system.v1.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 系统角色模型
 * @author hzh 2018/8/20 下午10:56
 */
@Data
@Document(collection = "systemRole")
public class SystemRole {

	private String id;
	private String name;
}
