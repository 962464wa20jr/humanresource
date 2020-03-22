package com.weinan.service.impl;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.weinan.common.util.AgeUtils;
import com.weinan.dao.UserDao;
import com.weinan.entity.User;
import com.weinan.exception.LoginException;
import com.weinan.service.UserService;
import com.weinan.vo.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao userDao;

	@Override
	public User findUserByStaffCode(String staffCode) {
		// TODO Auto-generated method stub
		return userDao.findUserByStaffCode(staffCode);
	}

	@Override
	public UserVO login(String userName, String password) {
		// TODO Auto-generated method stub
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
		UserVO userVO = new UserVO();
		try{
			currentUser.login(token);
			User user = (User)currentUser.getPrincipals().getPrimaryPrincipal();
			if(user.getStatus() == 0 || user.getStatus() == 2) {
				if(user.getStatus() == 0) userVO.setStatus((byte)0);
				else userVO.setStatus((byte)2);
				return userVO;
			}
			userVO.setToken(currentUser.getSession().getId().toString());
			BeanUtils.copyProperties(user, userVO);
			System.out.println(userVO.getCountry());
			System.out.println(user.getDepartmentName());
			if(userVO.getSex() != null) {
				if(userVO.getSex().equals((byte)1)){
					userVO.setSexStr("男");
				}else {
					userVO.setSexStr("女");
				}
			}
			if(userVO.getIsMarried() != null) {
				if(userVO.getIsMarried().equals((byte)1)){
					userVO.setIsMarriedStr("未婚");
				}else {
					userVO.setIsMarriedStr("已婚");
				}
			}
			if(userVO.getEducation() != null) {
				switch(user.getEducation()) {
					case 1:
						userVO.setEducationStr("专科");
						break;
					case 2:
						userVO.setEducationStr("本科");
						break;
					case 3:
						userVO.setEducationStr("硕士");
						break;
					case 4:
						userVO.setEducationStr("博士");
						break;
					default:
						 break;
				}
			}
			if(userVO.getBirthday() != null) {
				userVO.setAge(AgeUtils.getAgeByBirthday(userVO.getBirthday()));
			}
//			if(userVO.getBirthday() != null) {
//				String pattern = "YYYY-MM-DD";
//				SimpleDateFormat sdf = new SimpleDateFormat(pattern); 
//				userVO.setBirthday(sdf.format(userVO.getBirthday()));
//			}
			//System.out.println(userVO.getRoles().get(0).getRole());
		}catch(UnknownAccountException e) {
			throw new LoginException("账户不存在!",e);
		}catch(IncorrectCredentialsException e) {
			throw new LoginException("账号密码错误!",e);
		}catch(AuthenticationException e) {
			throw new LoginException("账号验证失败!",e);
		}
		return userVO;
	}

	@Override
	public void logOut() {
		// TODO Auto-generated method stub
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		
	}
	
	
	@Override
	public Long insert(User user) {
		// TODO Auto-generated method stub
		return userDao.insert(user);
	}

	@Override
	public User findUserByUserId(Long userId) {
		// TODO Auto-generated method stub
		return userDao.findUserByUserId(userId);
	}

	@Override
	public int update(User user) {
		// TODO Auto-generated method stub
		return userDao.update(user);
	}

	
}
