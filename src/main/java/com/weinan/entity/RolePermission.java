package com.weinan.entity;

import java.math.BigInteger;

import com.weinan.common.entity.BasicEntity;

public class RolePermission extends BasicEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigInteger id; //id
	private BigInteger roleId; //角色ID
	private BigInteger permissionId; //权限ID
	public BigInteger getRoleId() {
		return roleId;
	}
	public void setRoleId(BigInteger roleId) {
		this.roleId = roleId;
	}
	public BigInteger getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(BigInteger permissionId) {
		this.permissionId = permissionId;
	}
	
	

}
