package com.base.modules.system.controller;

import com.base.modules.system.v1.bo.auth.AuthTree;
import com.base.modules.system.v1.dto.auth.SaveSystemAuth;
import com.base.modules.system.v1.dto.auth.SystemAuthInfo;
import com.base.modules.system.v1.service.SystemAuthV1Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

/**
 * 权限相关控制层
 * @author hzh 2018/8/21 下午10:20
 */
@RestController
public class AuthController {

	private SystemAuthV1Service systemAuthV1Service;

	public AuthController(SystemAuthV1Service systemAuthV1Service) {
		this.systemAuthV1Service = systemAuthV1Service;
	}

	/**
	 * 资源权限数据树
	 * @return 资源权限数据树
	 * @author hzh
	 */
	@GetMapping("/auth/tree")
	public Mono<ResponseEntity<List<AuthTree>>> authList() {
		return this.systemAuthV1Service.authList();
	}

	/**
	 * 保存权限
	 * @param saveSystemAuth 保存权限信息
	 * @return 权限信息
	 * @author hzh
	 */
	@PostMapping("/auth")
	public Mono<ResponseEntity<SystemAuthInfo>> save(@RequestBody @Valid SaveSystemAuth saveSystemAuth) {
		return this.systemAuthV1Service.save(saveSystemAuth);
	}

	/**
	 * 获取权限信息
	 * @param id 权限ID
	 * @return 权限信息
	 * @author hzh
	 */
	@GetMapping("/auth/{id}")
	public Mono<ResponseEntity<SystemAuthInfo>> info(@PathVariable("id") String id) {
		return this.systemAuthV1Service.info(id);
	}

	/**
	 * 更新权限信息
	 * @param saveSystemAuth 更新的权限信息
	 * @return 更新结果
	 * @author hzh
	 */
	@PutMapping("/auth")
	public Mono<ResponseEntity<Void>> update(@RequestBody @Valid SaveSystemAuth saveSystemAuth) {
		return this.systemAuthV1Service.update(saveSystemAuth);
	}

	/**
	 * 删除权限信息
	 * @param id 权限ID
	 * @return 返回删除结果
	 * @author hzh
	 */
	@DeleteMapping("/auth/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id) {
		return this.systemAuthV1Service.delete(id);
	}
}
