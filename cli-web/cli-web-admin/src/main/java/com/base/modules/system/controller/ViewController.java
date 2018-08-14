package com.base.modules.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 视图相关控制器
 * @author hzh 2018/8/14 下午10:28
 */
@Controller
public class ViewController {

	/**
	 * 首页
	 * @return
	 */
	@GetMapping(value = {"/", "/index"})
	public String index() {
		return "index";
	}
}
