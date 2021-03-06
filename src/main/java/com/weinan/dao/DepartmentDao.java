package com.weinan.dao;

import java.math.BigInteger;

import org.springframework.stereotype.Repository;

import com.weinan.entity.Department;

@Repository
public interface DepartmentDao {
	
	public Department getDepartmentById(Long departmentId);
	public Long getIdByDepartmentName(String department);

}
