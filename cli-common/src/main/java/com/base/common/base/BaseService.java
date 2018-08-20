package com.base.common.base;

import com.base.common.utils.string.StringUtils;

import java.util.function.Consumer;

/**
 * 业务层基类
 * @author hzh 2018/8/19 上午12:28
 */
public class BaseService {

	/**
	 * 是否拥有查询条件
	 * @param condition 查询条件
	 * @param consumer 条件消费者
	 */
	public void hasQueryCondition(String condition, Consumer<String> consumer) {
		if (!StringUtils.isEmpty(condition)) {
			consumer.accept(condition);
		}
	}
}
