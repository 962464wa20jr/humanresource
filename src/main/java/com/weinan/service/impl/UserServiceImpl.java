package com.weinan.service.impl;

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
				userVO.setAge(getAgeByBirthday(userVO.getBirthday()));
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
	
	public int getAgeByBirthday(Date birthday) {
		int age = 0;
		Calendar cal = Calendar.getInstance();
		if(cal.before(birthday)) {
			throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
		}
		int currentYear = cal.get(Calendar.YEAR);
		int currentMonth = cal.get(Calendar.MONTH);
		int currentDay = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(birthday);
		int birthYear = cal.get(Calendar.YEAR);
		int birthMonth = cal.get(Calendar.MONTH);
		int birthDay = cal.get(Calendar.DAY_OF_MONTH);
		age = currentYear - birthYear;
		if(currentMonth <= birthMonth) {
			if(currentMonth == birthMonth) {
				if(currentDay < birthDay) {
					age--;
				}
			}else {
				age--;
			}
		}
		return age;
	}

	
}
