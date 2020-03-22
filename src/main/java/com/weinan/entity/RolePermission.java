package com.weinan.entity;

import java.math.BigInteger;

import com.weinan.common.entity.BasicEntity;

public class RolePermission extends BasicEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id; //id
	private Long roleId; //角色ID
	private Long permissionId; //权限ID
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}
	
	

}
