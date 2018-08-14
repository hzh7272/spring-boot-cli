package com.base.modules.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 系统用户相关控制层
 * @author hzh 2018/8/13 下午10:17
 */
@Controller
public class UserController {

	/**
	 * 系统用户管理页面
	 * @return
	 */
	@GetMapping("/system/userManage")
	public String userManage() {
		return "modules/system/userManage";
	}
}
