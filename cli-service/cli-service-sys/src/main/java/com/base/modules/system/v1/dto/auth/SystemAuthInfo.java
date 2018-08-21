package com.base.modules.system.v1.dto.auth;

import lombok.Data;

/**
 * 系统权限详情数据传输模型
 * @author hzh 2018/8/20 下午11:40
 */
@Data
public class SystemAuthInfo {

    private String id;
    private String parentId;
    private String species;
    private Integer type;
    private String name;
    private String resource;
    private String icon;
    private String permission;
    private Integer sort;
    private Integer state;
}
