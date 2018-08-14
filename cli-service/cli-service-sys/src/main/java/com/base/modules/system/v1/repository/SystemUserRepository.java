package com.base.modules.system.v1.repository;

import com.base.modules.system.v1.model.SystemUser;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * 系统用户数据操作接口
 * @author hzh 2018/8/14 12:52
 */
@Repository
public interface SystemUserRepository extends ReactiveMongoRepository<SystemUser, String> {

    Mono<SystemUser> findSystemUsersByNickName(String nickName);
}
