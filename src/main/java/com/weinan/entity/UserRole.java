package com.weinan.entity;

import java.math.BigInteger;

import com.weinan.common.entity.BasicEntity;

public class UserRole extends BasicEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Long id; //id
    private Long userId; //用户ID
    private Long roleId; //角色ID
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
    
    
}
