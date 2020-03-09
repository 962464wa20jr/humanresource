package com.weinan.entity;

import java.math.BigInteger;

import com.weinan.common.entity.BasicEntity;

public class UserRole extends BasicEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private BigInteger id; //id
    private BigInteger userId; //用户ID
    private BigInteger roleId; //角色ID
	public BigInteger getUserId() {
		return userId;
	}
	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}
	public BigInteger getRoleId() {
		return roleId;
	}
	public void setRoleId(BigInteger roleId) {
		this.roleId = roleId;
	}
    
    
}
