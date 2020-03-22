package com.weinan.service.impl;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.weinan.dao.RoleDao;
import com.weinan.entity.Role;
import com.weinan.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	
	@Resource
	private RoleDao roleDao;

	@Override
	public List<Role> getUserRole(Long userId) {
		// TODO Auto-generated method stub
		return roleDao.getUserRole(userId);
	}

	@Override
	public Role getRoleByName(String role) {
		// TODO Auto-generated method stub
		return roleDao.getRoleByName(role);
	}

	@Override
	public int insertUserRole(Long userId, Long roleId) {
		// TODO Auto-generated method stub
		return roleDao.insertUserRole(userId, roleId);
	}

	@Override
	public List<Role> getAllRole() {
		// TODO Auto-generated method stub
		return roleDao.getAllRole();
	}

}
