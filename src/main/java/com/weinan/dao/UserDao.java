package com.weinan.dao;

import java.math.BigInteger;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.weinan.entity.User;
import com.weinan.vo.UserVO;

@Repository
public interface UserDao {
	
	public User findUserByStaffCode(String staffCode);
	public Long insert(User user);
	public User findUserByUserId(Long userId);
	public int update(User user);

}
