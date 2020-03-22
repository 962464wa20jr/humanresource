package com.weinan.entity;

import java.math.BigInteger;

import com.weinan.common.entity.BasicEntity;

public class Permission extends BasicEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id; //权限编号
	private String permissionName; //权限名称
	private String permission; //权限
	private String url; //对应的url
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
