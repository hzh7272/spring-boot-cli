package com.base.common.base;

import lombok.Data;

import java.time.ZonedDateTime;

/**
 * 持久化数据模型基类
 * @author hzh 2018/8/2 13:17
 */
@Data
public class BaseModel {

    private ZonedDateTime createTime;
    private Long createUserId;
    private ZonedDateTime modifyTime;
    private Long modifyUserId;
}
