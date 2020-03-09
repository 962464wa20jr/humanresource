package com.weinan.service.impl;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.weinan.dao.PermissionDao;
import com.weinan.entity.Permission;
import com.weinan.service.PermissionService;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

	@Resource
	private PermissionDao permissionDao;
	
	@Override
	public List<Permission> getRolePermission(BigInteger roleId) {
		// TODO Auto-generated method stub
		return permissionDao.getRolePermission(roleId);
	}

}
