package com.weinan.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weinan.common.result.CommResult;
import com.weinan.entity.Role;
import com.weinan.service.RoleService;

@RestController
public class RoleController {

	@Resource
	private RoleService roleService;
	
	@RequestMapping("/getRole")
	public CommResult getRole() {
		List<Role> roleList = roleService.getAllRole();
		return CommResult.ok(roleList);
	}
}
