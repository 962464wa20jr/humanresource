package com.weinan.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weinan.common.Contant;
import com.weinan.common.result.CommResult;
import com.weinan.exception.LoginException;
import com.weinan.service.UserService;
import com.weinan.vo.UserVO;

import io.swagger.annotations.ApiParam;

@RestController
public class LoginController {
	@Resource
	private UserService userService;
	
	@PostMapping("/login")
	public CommResult login(HttpServletRequest request,
			   @ApiParam(value = "工号",required = true)@RequestParam(value = "staffCode",required = true)String userName,
			   @ApiParam(value = "登录密码",required = true)@RequestParam(value = "password",required = true)String password) {
		if(StringUtils.isBlank(userName)) {
			return CommResult.error("用户名为空！");
		}
		if(StringUtils.isBlank(password)) {
			return CommResult.error("密码为空！");
		}
		
		try{
			UserVO loginUser = userService.login(userName, password); 
			return CommResult.ok(loginUser);
		}catch(LoginException e) {
			return CommResult.error(e.getMessage());
		}
		
	}
	
	@PostMapping("/logout")
	public CommResult logout() {
		return CommResult.ok();
	}
	
	
	@RequestMapping("/logout")
	public CommResult logOut() {
		userService.logOut();
		return CommResult.ok();
	}
	
	@RequestMapping("/unauth")
	public CommResult unauthorized() {
		return CommResult.error(Contant.RESULT_UNAUTHORIZED,Contant.RESULT_UNAUTHORIZED_TEXT);
	}

}
