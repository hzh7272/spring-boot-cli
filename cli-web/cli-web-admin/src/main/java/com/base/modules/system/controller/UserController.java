package com.base.modules.system.controller;

import com.base.modules.system.v1.model.SystemUser;
import com.base.modules.system.v1.repository.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * 系统用户相关控制层
 * @author hzh 2018/8/13 下午10:17
 */
@Controller
public class UserController {

	@Autowired
	private SystemUserRepository systemUserRepository;

	/**
	 * 系统用户管理页面
	 * @return
	 */
	@GetMapping("/system/userManage")
	public String userManage() {
		return "modules/system/userManage";
	}

	@ResponseBody
	@PostMapping("/system/user")
	public Mono<SystemUser> save(@RequestBody SystemUser systemUser) {
		systemUser.setCreateTime(new Date());
		return systemUserRepository.save(systemUser);
	}

	@ResponseBody
	@GetMapping("/system/user")
	public Mono<SystemUser> get(String name) {
		return systemUserRepository.findSystemUsersByNickName(name);
	}
}
