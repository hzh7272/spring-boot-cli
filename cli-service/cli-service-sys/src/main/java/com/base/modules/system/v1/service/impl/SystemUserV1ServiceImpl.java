package com.base.modules.system.v1.service.impl;

import com.base.common.base.BaseService;
import com.base.common.utils.data.Page;
import com.base.modules.system.v1.dto.user.SaveSystemUser;
import com.base.modules.system.v1.dto.user.SystemUserInfo;
import com.base.modules.system.v1.dto.user.SystemUserSearch;
import com.base.modules.system.v1.model.SystemUser;
import com.base.modules.system.v1.repository.SystemUserV1Repository;
import com.base.modules.system.v1.service.SystemUserV1Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 系统管理员相关业务实现层
 * @author hzh 2018/8/18 下午3:51
 */
@Slf4j
@Service
public class SystemUserV1ServiceImpl extends BaseService implements SystemUserV1Service {

	private MongoTemplate mongoTemplate;
	private SystemUserV1Repository systemUserV1Repository;

	public SystemUserV1ServiceImpl(MongoTemplate mongoTemplate, SystemUserV1Repository systemUserV1Repository) {
		this.mongoTemplate = mongoTemplate;
		this.systemUserV1Repository = systemUserV1Repository;
	}

	/**
	 * 系统管理员列表
	 * @param systemUserSearch 系统管理员查询条件数据模型
	 * @param page 分页
	 * @return 系统管理员列表
	 * @author hzh
	 */
	@Override
	public Mono<ResponseEntity<Page<SystemUserInfo>>> getSystemUserList(SystemUserSearch systemUserSearch, Page<SystemUserInfo> page) {
		return Mono.fromSupplier(() -> {
			// 创建排序方式，根据创建时间倒序
			var sort = new Sort(Sort.Direction.DESC, "createTime");
			// 分页
			var pageRequest = PageRequest.of(page.getPageNo(), page.getRows(), sort);
			// 创建Criteria查询
			var criteria = Criteria.where("state").ne(0);
			hasQueryCondition(systemUserSearch.getKeyword(), keyword -> {
				var pattern = Pattern.compile("^.*" + keyword + ".*$", Pattern.CASE_INSENSITIVE);
				criteria.orOperator(Criteria.where("account").regex(pattern),
						Criteria.where("nickName").regex(pattern));
			});
			hasQueryCondition(systemUserSearch.getRoleId(), roleId -> {
				criteria.andOperator(Criteria.where("roleId").is(roleId));
			});

			// 创建查询对象
			var query = new Query();
			query.addCriteria(criteria);

	 		// 查询数据记录数
			var totalCountMono = mongoTemplate.count(query, SystemUser.class);
			if (0 < totalCountMono) {
				page.setTotalCount(totalCountMono);
				var data = mongoTemplate.find(query.with(pageRequest), SystemUser.class).stream()
						.map(systemUser -> {
							var systemUserInfo = new SystemUserInfo();
							BeanUtils.copyProperties(systemUser, systemUserInfo);
							return systemUserInfo;
						}).collect(Collectors.toList());

				page.setData(data);
			}

			return new ResponseEntity<>(page, HttpStatus.OK);
		});
	}

	/**
	 * 保存系统管理员
	 * @param saveSystemUser 保存系统用户数据模型
	 * @return	系统用户信息数据模型
	 * @author hzh
	 */
	@Override
	public Mono<ResponseEntity<SystemUserInfo>> save(SaveSystemUser saveSystemUser) {
		var systemUser = new SystemUser();
		BeanUtils.copyProperties(saveSystemUser, systemUser);

		// 保存数据，id需要zh置空
		systemUser.setId(null);
		return this.systemUserV1Repository.save(systemUser)
				.map(s -> {
					var systemUserInfo = new SystemUserInfo();
					BeanUtils.copyProperties(s, systemUserInfo);
					return new ResponseEntity<>(systemUserInfo, HttpStatus.OK);
				});
	}
}
