package com.weinan.service;

import java.math.BigInteger;

import com.weinan.entity.User;
import com.weinan.vo.UserVO;

public interface UserService {
	
	public User findUserByStaffCode(String staffCode);
	public UserVO login(String userName,String password);
	public void logOut();
	public Long insert(User user);
	public User findUserByUserId(Long userId);
	public int update(User user);

}
