package com.weinan.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.weinan.entity.Permission;

@Repository
public interface PermissionDao {
	
	public List<Permission> getRolePermission(Long roleId);

}
