package com.weinan.service.impl;

import java.math.BigInteger;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.weinan.dao.DepartmentDao;
import com.weinan.entity.Department;
import com.weinan.service.DepartmentService;


@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
	
	@Resource
	private DepartmentDao departmentDao;

	@Override
	public Department getDepartmentById(Long departmentId) {
		// TODO Auto-generated method stub
		return departmentDao.getDepartmentById(departmentId);
	}

	@Override
	public Long getIdByDepartmentName(String department) {
		// TODO Auto-generated method stub
		return departmentDao.getIdByDepartmentName(department);
	}

}
