package com.weinan.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.weinan.entity.User;

@Repository
public interface UserDao {
	
	public User findUserByStaffCode(String staffCode);
	public Map<String,Object> findUserVoByStaffCode(String staffCode);

}
