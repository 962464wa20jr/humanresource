package com.weinan.dao;

import org.springframework.stereotype.Repository;

import com.weinan.entity.RegisterTransaction;

@Repository
public interface RegisterDao {
	
	public Long insert(RegisterTransaction entity);

}
