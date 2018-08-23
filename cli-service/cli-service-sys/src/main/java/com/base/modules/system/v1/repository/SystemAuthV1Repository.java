package com.base.modules.system.v1.repository;

import com.base.modules.system.v1.model.SystemAuth;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 系统权限数据操作接口
 * @author hzh 2018/8/20 下午11:37
 */
@Repository
public interface SystemAuthV1Repository extends ReactiveMongoRepository<SystemAuth, String> {

}
