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
	 * 主页
	 * @return 视图
	 */
	@GetMapping(value = {"/", "/index"})
	public String index() {
		return "index";
	}

	/**
	 * 首页
	 * @return 视图
	 */
	@GetMapping(value = "/home")
	public String home() {
		return "home";
	}

	/**
	 * 系统用户管理页面
	 * @return
	 */
	@GetMapping("/system/userManage")
	public String userManage() {
		return "modules/system/userManage";
	}
}
