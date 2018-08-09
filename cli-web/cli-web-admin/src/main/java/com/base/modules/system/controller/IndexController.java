package com.base.modules.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页
 * @author hzh 2018/8/9 下午9:18
 */
@Controller
public class IndexController {

	@GetMapping("/index")
	public String index() {
		return "index";
	}
}
