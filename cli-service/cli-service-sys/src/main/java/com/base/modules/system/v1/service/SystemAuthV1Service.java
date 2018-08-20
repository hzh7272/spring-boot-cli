package com.base.modules.system.v1.service;

import com.base.common.utils.data.Page;
import com.base.modules.system.v1.dto.auth.SaveSystemAuth;
import com.base.modules.system.v1.dto.auth.SystemAuthInfo;
import com.base.modules.system.v1.dto.auth.SystemAuthSearch;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

/**
 * 系统权限相关业务接口层
 * @author hzh 2018/8/20 下午11:38
 */
public interface SystemAuthV1Service {

	/**
	 * 系统权限信息列表
	 * @param systemAuthSearch 系统权限查询条件
	 * @return 系统权限信息列表
	 * @author hzh
	 */
	Mono<ResponseEntity<Page<SystemAuthInfo>>> authList(SystemAuthSearch systemAuthSearch);

	/**
	 * 保存系统权限
	 * @param saveSystemAuth 系统权限保存数据模型
	 * @return 系统权限信息
	 * @author hzh
	 */
	Mono<ResponseEntity<SystemAuthInfo>> save(SaveSystemAuth saveSystemAuth);

	/**
	 * 修改系统权限
	 * @param saveSystemAuth 系统权限保存数据模型
	 * @return 修改结果
	 * @author hzh
	 */
	Mono<ResponseEntity<Void>> update(SaveSystemAuth saveSystemAuth);

	/**
	 * 删除系统权限
	 * @param id 系统权限ID
	 * @return 删除结果
	 * @author hzh
	 */
	Mono<ResponseEntity<Void>> delete(String id);
}
