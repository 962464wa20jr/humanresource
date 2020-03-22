package com.weinan.service;



import com.weinan.entity.Department;

public interface DepartmentService {
	
	public Department getDepartmentById(Long departmentId);
	public Long getIdByDepartmentName(String department);

}
