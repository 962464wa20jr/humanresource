package com.weinan.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.weinan.dao.RegisterDao;
import com.weinan.entity.RegisterTransaction;
import com.weinan.service.RegisterService;

@Service("registerService")
public class RegisterServiceImpl implements RegisterService {
	
	@Resource
	private RegisterDao registerDao;

	@Override
	public Long insert(RegisterTransaction entity) {
		// TODO Auto-generated method stub
		return registerDao.insert(entity);
	}

}
