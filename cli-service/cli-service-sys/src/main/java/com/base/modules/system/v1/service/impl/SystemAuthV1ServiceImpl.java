package com.base.modules.system.v1.service.impl;

import com.base.common.utils.string.StringUtils;
import com.base.modules.system.v1.bo.auth.AuthTree;
import com.base.modules.system.v1.dto.auth.SaveSystemAuth;
import com.base.modules.system.v1.dto.auth.SystemAuthInfo;
import com.base.modules.system.v1.model.SystemAuth;
import com.base.modules.system.v1.repository.SystemAuthV1Repository;
import com.base.modules.system.v1.service.SystemAuthV1Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统权限相关业务实现层
 * @author hzh 2018/8/20 下午11:55
 */
@Slf4j
@Service
public class SystemAuthV1ServiceImpl implements SystemAuthV1Service {

	private MongoTemplate mongoTemplate;
	private SystemAuthV1Repository systemAuthV1Repository;

	public SystemAuthV1ServiceImpl(MongoTemplate mongoTemplate, SystemAuthV1Repository systemAuthV1Repository) {
		this.mongoTemplate = mongoTemplate;
		this.systemAuthV1Repository = systemAuthV1Repository;
	}

	/**
	 * 系统权限信息列表
	 * @return 系统权限信息列表
	 * @author hzh
	 */
	@Override
	public Mono<ResponseEntity<List<AuthTree>>> authList() {
		return Mono.fromSupplier(() -> {
			// 创建排序方式，根据创建时间倒序
			var sort = new Sort(Sort.Direction.ASC, "sort", "createTime");
			// 查询条件
			var criteria = Criteria.where("state").ne(0);

			var query = new Query();
			query.addCriteria(criteria);

			List<SystemAuthInfo> systemAuthList = mongoTemplate.find(query.with(sort), SystemAuthInfo.class, "systemAuth");

			// 第一层
			var topAuthList = systemAuthList.stream()
					.filter(systemAuthInfo -> StringUtils.isEmpty(systemAuthInfo.getParentId()))
					.map(systemAuthInfo -> {
						var topAuthTree = new AuthTree();
						topAuthTree.setId(systemAuthInfo.getId());
						topAuthTree.setParentId(systemAuthInfo.getParentId());
						topAuthTree.setTitle(systemAuthInfo.getName());
						topAuthTree.setExpand(true);
						return topAuthTree;
					}).collect(Collectors.toList());

			// 递归构建权限数据树
			topAuthList.forEach(authTree -> recursiveAuth(authTree, systemAuthList));

			return new ResponseEntity<>(topAuthList, HttpStatus.OK);
		});
	}

	/**
	 * 递归构建权限数据树
	 * @param authTree 权限数据树
	 * @param systemAuthList 权限数据列表
	 * @author hzh
	 */
	private void recursiveAuth(AuthTree authTree, List<SystemAuthInfo> systemAuthList) {
		List<AuthTree> children = systemAuthList.stream()
				.filter(sai -> !StringUtils.isEmpty(sai.getParentId()) && sai.getParentId().equals(authTree.getId()))
				.map(sai -> {
					var childAuthTree = new AuthTree();
					childAuthTree.setId(sai.getId());
					childAuthTree.setParentId(sai.getParentId());
					childAuthTree.setTitle(sai.getName());
					childAuthTree.setExpand(false);

					recursiveAuth(childAuthTree, systemAuthList);
					return childAuthTree;
		}).collect(Collectors.toList());

		authTree.setChildren(children);
	}

	/**
	 * 保存系统权限
	 * @param saveSystemAuth 系统权限保存数据模型
	 * @return 系统权限信息
	 * @author hzh
	 */
	@Override
	public Mono<ResponseEntity<SystemAuthInfo>> save(SaveSystemAuth saveSystemAuth) {
		var systemAuth = new SystemAuth();
		BeanUtils.copyProperties(saveSystemAuth, systemAuth);
		systemAuth.setId(null);
		return this.systemAuthV1Repository.save(systemAuth).map(s -> {
			var systemAuthInfo = new SystemAuthInfo();
			BeanUtils.copyProperties(s, systemAuthInfo);
			return ResponseEntity.ok(systemAuthInfo);
		});
	}

	/**
	 * 获取系统权限详情
	 * @param id 系统权限ID
	 * @return 系统权限信息
	 * @author hzh
	 */
	@Override
	public Mono<ResponseEntity<SystemAuthInfo>> info(String id) {
		return this.systemAuthV1Repository.findById(id).flatMap(auth -> {
			var systemAuthInfo = new SystemAuthInfo();
			BeanUtils.copyProperties(auth, systemAuthInfo);
			return Mono.just(new ResponseEntity<>(systemAuthInfo, HttpStatus.OK));
		}).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
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
					systemAuth.setId("5b7d791f1ff06323c0e73269");
					return this.systemAuthV1Repository.save(systemAuth)
							.then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)));
				}).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * 检查系统权限
	 * @param id 权限ID
	 * @return 返回子权限数量
	 * @author hzh
	 */
	@Override
	public Mono<Integer> checkAuth(String id) {
		return Mono.fromSupplier(() -> {
			var criteria = Criteria.where("parentId").is(id);
			var query = new Query();
			query.addCriteria(criteria);

			int count = (int) mongoTemplate.count(query, SystemAuth.class);
			return count;
		});
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
