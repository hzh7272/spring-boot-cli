package com.base.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

/**
 * 异常统一处理切面
 * @author hzh 2018/8/18 下午4:17
 */
@RestControllerAdvice
public class ExceptionAdvice {

	/**
	 * 数据校验异常处理
	 * @param e 数据校验异常
	 * @return 错误信息
	 */
	@ExceptionHandler(WebExchangeBindException.class)
	public ResponseEntity<String> handleBindException(
			WebExchangeBindException e) {
		String defaultMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
		return new ResponseEntity<>(defaultMessage, HttpStatus.BAD_REQUEST);
	}
}
