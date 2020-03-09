package com.weinan.service;

import com.weinan.entity.User;
import com.weinan.vo.UserVO;

public interface UserService {
	
	public User findUserByStaffCode(String staffCode);
	public UserVO login(String userName,String password);
	public void logOut();

}
