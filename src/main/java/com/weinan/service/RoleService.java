package com.weinan.service;

import java.math.BigInteger;
import java.util.List;

import com.weinan.entity.Role;

public interface RoleService {
	
	public List<Role> getUserRole(Long userId);
	public Role getRoleByName(String role);
	public int insertUserRole(Long userId,Long roleId);
	public List<Role> getAllRole();

}
