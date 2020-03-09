package com.weinan.vo;



import java.io.Serializable;
import java.util.Date;



public class DepartmentVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String departmentName;//部门名称
	private int departmentCount;//部门人数
	private String departmenteDesc;//部门简述
	private Date createTime;//创建时间
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
	
}
