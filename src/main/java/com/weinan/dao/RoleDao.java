package com.weinan.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.weinan.entity.Role;

@Repository
public interface RoleDao {
	
	public List<Role> getUserRole(Long userId);
	public Role getRoleByName(String role);
	public int insertUserRole(@Param("userId")Long userId,@Param("roleId")Long roleId);
	public List<Role> getAllRole();

}
