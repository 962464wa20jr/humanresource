package com.weinan.service;

import java.math.BigInteger;
import java.util.List;

import com.weinan.entity.Permission;

public interface PermissionService {
	
	public List<Permission> getRolePermission(Long roleId);

}
