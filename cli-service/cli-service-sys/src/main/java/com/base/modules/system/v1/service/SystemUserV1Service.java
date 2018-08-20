package com.base.modules.system.v1.service;

import com.base.common.utils.data.Page;
import com.base.modules.system.v1.dto.SaveSystemUser;
import com.base.modules.system.v1.dto.SystemUserInfo;
import com.base.modules.system.v1.dto.SystemUserSearch;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

/**
 * 系统管理员相关业务接口层
 * @author hzh 2018/8/18 下午3:47
 */
public interface SystemUserV1Service {

	/**
	 * 系统管理员列表
	 * @param systemUserSearch 系统管理员查询条件数据模型
	 * @param page 分页
	 * @return 系统管理员列表
	 * @author hzh
	 */
	Mono<ResponseEntity<Page<SystemUserInfo>>> getSystemUserList(SystemUserSearch systemUserSearch, Page<SystemUserInfo> page);

	/**
	 * 保存系统管理员
	 * @param saveSystemUser 保存系统用户数据模型
	 * @return	系统用户信息数据模型
	 * @author hzh
	 */
	Mono<ResponseEntity<SystemUserInfo>> save(SaveSystemUser saveSystemUser);
}
