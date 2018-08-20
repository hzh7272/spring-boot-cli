package com.base.modules.system.v1.service.impl;

import com.base.common.utils.data.Page;
import com.base.modules.system.v1.dto.auth.SaveSystemAuth;
import com.base.modules.system.v1.dto.auth.SystemAuthInfo;
import com.base.modules.system.v1.dto.auth.SystemAuthSearch;
import com.base.modules.system.v1.model.SystemAuth;
import com.base.modules.system.v1.repository.SystemAuthV1Repository;
import com.base.modules.system.v1.service.SystemAuthV1Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * 系统权限相关业务实现层
 * @author hzh 2018/8/20 下午11:55
 */
@Slf4j
@Service
public class SystemAuthV1ServiceImpl implements SystemAuthV1Service {

	@Autowired
	private SystemAuthV1Repository systemAuthV1Repository;

	/**
	 * 系统权限信息列表
	 * @param systemAuthSearch 系统权限查询条件
	 * @return 系统权限信息列表
	 * @author hzh
	 */
	@Override
	public Mono<ResponseEntity<Page<SystemAuthInfo>>> authList(SystemAuthSearch systemAuthSearch) {
		return null;
	}

	/**
	 * 保存系统权限
	 * @param saveSystemAuth 系统权限保存数据模型
	 * @return 系统权限信息
	 * @author hzh
	 */
	@Override
	public Mono<ResponseEntity<SystemAuthInfo>> save(SaveSystemAuth saveSystemAuth) {
		SystemAuth systemAuth = new SystemAuth();
		BeanUtils.copyProperties(saveSystemAuth, systemAuth);
		return this.systemAuthV1Repository.save(systemAuth).map(s -> {
			SystemAuthInfo systemAuthInfo = new SystemAuthInfo();
			BeanUtils.copyProperties(s, systemAuthInfo);
			return ResponseEntity.ok(systemAuthInfo);
		});
	}

	/**
	 * 修改系统权限
	 * @param saveSystemAuth 系统权限保存数据模型
	 * @return 修改结果
	 * @author hzh
	 */
	@Override
	public Mono<ResponseEntity<Void>> update(SaveSystemAuth saveSystemAuth) {
		return this.systemAuthV1Repository.findById(saveSystemAuth.getId())
				.flatMap(systemAuth -> {
					BeanUtils.copyProperties(saveSystemAuth, systemAuth);
					return this.systemAuthV1Repository.save(systemAuth)
							.then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)));
				}).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * 删除系统权限
	 * @param id 系统权限ID
	 * @return 删除结果
	 * @author hzh
	 */
	@Override
	public Mono<ResponseEntity<Void>> delete(String id) {
		return this.systemAuthV1Repository.findById(id)
				.flatMap(systemAuth -> this.systemAuthV1Repository.delete(systemAuth)
						.then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
