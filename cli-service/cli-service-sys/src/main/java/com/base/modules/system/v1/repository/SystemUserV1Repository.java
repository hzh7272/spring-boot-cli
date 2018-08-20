package com.base.modules.system.v1.repository;

import com.base.modules.system.v1.model.SystemUser;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * 系统用户数据操作接口
 * @author hzh 2018/8/14 12:52
 */
@Repository
public interface SystemUserV1Repository extends ReactiveMongoRepository<SystemUser, String> {

}
