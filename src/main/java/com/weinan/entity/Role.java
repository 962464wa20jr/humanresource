package com.weinan.entity;

import java.math.BigInteger;

import com.weinan.common.entity.BasicEntity;

public class Role extends BasicEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id; //角色编号
	private String role; //角色名称
	private String description; //角色描述
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
