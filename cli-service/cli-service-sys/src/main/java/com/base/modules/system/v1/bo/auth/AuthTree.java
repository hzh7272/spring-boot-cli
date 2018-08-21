package com.base.modules.system.v1.bo.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

/**
 * 权限数据树
 * @author hzh 2018/8/21 11:21
 */
@Data
public class AuthTree {

    private String id;
    @JsonIgnore
    private String parentId;
    private String title;
    private boolean expand;
    private List<AuthTree> children;
}
