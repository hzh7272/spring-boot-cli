package com.base.common.utils.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

/**
 * 分页数据模型
 * @author hzh 2018/8/19 上午12:09
 */
@Data
public class Page<R> {

	private Integer page;
	private Integer rows;
	private List<R> data;
	private Long totalCount;

	@JsonIgnore
	public Integer getPageNo() {
		return null == this.page ? 0 : (this.page - 1) * this.getRows();
	}

	public Integer getRows() {
		return null == this.rows ? 10 : this.rows;
	}
}
