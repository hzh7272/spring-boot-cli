package com.base.modules.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 登录登出相关控制层
 * @author hzh 2018/8/8 15:42
 */
@Controller
public class SignController {

	/**
	 * 登录页面
	 * @return
	 */
	@GetMapping("/sign")
	public String sign() {
		return "sign";
	}
}