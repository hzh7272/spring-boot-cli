package com.base.modules.system.v1.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 系统用户模型
 * @author hzh 2018/8/14 12:42
 */
@Data
@Document(collection = "systemUser")
public class SystemUser {

    @Id
    private String id;
    private String roleId;
    private String account;
    private String nickName;
    private String avatarUrl;
    private String password;
    private Date createTime;
    private Integer state;
}
