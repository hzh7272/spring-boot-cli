package com.base.modules.system.controller;

import com.base.common.utils.data.Page;
import com.base.modules.system.v1.dto.user.SaveSystemUser;
import com.base.modules.system.v1.dto.user.SystemUserInfo;
import com.base.modules.system.v1.dto.user.SystemUserSearch;
import com.base.modules.system.v1.service.SystemUserV1Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * 系统用户相关控制层
 * @author hzh 2018/8/13 下午10:17
 */
@RestController
public class UserController {

	private SystemUserV1Service systemUserV1Service;

	public UserController(SystemUserV1Service systemUserV1Service) {
		this.systemUserV1Service = systemUserV1Service;
	}

	/**
	 * 系统管理员列表
	 * @param systemUserSearch 系统管理员查询条件数据模型
	 * @return 系统管理员列表
	 * @author hzh
	 */
	@GetMapping("/system/users")
	public Mono<ResponseEntity<Page<SystemUserInfo>>> getSystemUserList(SystemUserSearch systemUserSearch, Page<SystemUserInfo> page) {
		long start = System.currentTimeMillis();
		Mono<ResponseEntity<Page<SystemUserInfo>>> systemUserList = systemUserV1Service.getSystemUserList(systemUserSearch, page);
		System.out.println("调用时长：" + (System.currentTimeMillis() - start));
		return systemUserList;
	}

	/**
	 * 保存系统管理员
	 * @param saveSystemUser 保存系统用户数据模型
	 * @return	系统用户信息数据模型
	 * @author hzh
	 */
	@PostMapping("/system/user")
	public Mono<ResponseEntity<SystemUserInfo>> save(@RequestBody @Valid SaveSystemUser saveSystemUser) {
		long start = System.currentTimeMillis();
		Mono<ResponseEntity<SystemUserInfo>> save = systemUserV1Service.save(saveSystemUser);
		System.out.println("调用时长：" + (System.currentTimeMillis() - start));
		return save;
	}
}
