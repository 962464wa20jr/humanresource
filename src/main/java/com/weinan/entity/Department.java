package com.weinan.entity;

import java.math.BigInteger;
import java.util.Date;

import com.weinan.common.entity.BasicEntity;

public class Department extends BasicEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigInteger id;//部门Id
	private String departmentName;//部门名称
	private int departmentCount;//部门人数
	private String departmenteDesc;//部门简述
	private Date createTime;//创建时间
	private Date updateTime;//上次更新时间
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public int getDepartmentCount() {
		return departmentCount;
	}
	public void setDepartmentCount(int departmentCount) {
		this.departmentCount = departmentCount;
	}
	public String getDepartmenteDesc() {
		return departmenteDesc;
	}
	public void setDepartmenteDesc(String departmenteDesc) {
		this.departmenteDesc = departmenteDesc;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
