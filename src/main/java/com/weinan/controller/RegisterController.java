package com.weinan.controller;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weinan.common.result.CommResult;
import com.weinan.entity.Message;
import com.weinan.entity.RegisterTransaction;
import com.weinan.entity.User;
import com.weinan.service.DepartmentService;
import com.weinan.service.MessageService;
import com.weinan.service.RegisterService;
import com.weinan.service.RoleService;
import com.weinan.service.UserService;
import com.weinan.vo.UserVO;

import io.swagger.annotations.ApiParam;

@RestController
public class RegisterController {
	@Resource
	private UserService userService;
	
	@Resource
	private DepartmentService departmentService;
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private MessageService messageService;
	
	@Resource
	private RegisterService registerService;
	
	@PostMapping("/register")
	public CommResult register(HttpServletRequest request, @ApiParam(value = "注册姓名",required = true)@RequestParam(value = "name",required = true)String registerName,
			 @ApiParam(value = "工号",required = true)@RequestParam(value = "staffCode",required = true)String staffCode,
			   @ApiParam(value = "登录密码",required = true)@RequestParam(value = "password",required = true)String registerPassword,
			   @ApiParam(value = "部门",required = true)@RequestParam(value = "department",required = true)String department,
			   @ApiParam(value = "岗位",required = true)@RequestParam(value = "role",required = true)String role,
			   @ApiParam(value = "性别",required = true)@RequestParam(value = "sex",required = true)String sex,
			   @ApiParam(value = "手机号码",required = true)@RequestParam(value = "phone",required = true)String phone,
			   @ApiParam(value = "邮箱",required = true)@RequestParam(value = "email",required = true)String email,
			   @ApiParam(value = "生日",required = true)@RequestParam(value = "birthday",required = true)String birthday) {
		if(userService.findUserByStaffCode(staffCode) != null) {
			return CommResult.error("该账号已注册!");
		}
		User user = new User();
		Long departmentId = departmentService.getIdByDepartmentName(department);
		String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
		String credentialPassword = new SimpleHash("md5",registerPassword,staffCode+salt,2).toHex();
		user.setName(registerName);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		try {
			user.setBirthday(sdf.parse(birthday));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setSex(Byte.valueOf(sex));
		user.setPhoneNumber(phone);
		user.setEmail(email);
		user.setStaffCode(staffCode);
		user.setSalt(salt);
		user.setPassword(credentialPassword);
		user.setDepartmentId(departmentId);
		user.setStatus((byte)0);
		Long userId = userService.insert(user)+1;
		Long roleId = roleService.getRoleByName(role).getId();
		roleService.insertUserRole(userId, roleId);
		
		RegisterTransaction registerTransaction = new RegisterTransaction();
		registerTransaction.setApplyId(userId);
		registerTransaction.setApproverId(1L);
		registerTransaction.setCreateTime(new Date());
		
		Long transactionId = registerService.insert(registerTransaction);
		
		Message message = new Message();
		String event = department + "-" + role + "-" + staffCode + "-" + registerName + "注册申请";
		message.setEvent(event);
		message.setUserId(userId);
		message.setCreateTime(new Date());
		message.setType((byte)0);
		message.setTransactionId(transactionId);
		message.setUpdateTime(new Date());
		
		
		Long messageId = messageService.insert(message);
		
		messageService.insertApprover(messageId, 1L);
		
		
		return CommResult.ok();
		
	}
	
	@PostMapping("/aprroveRegister")
	public CommResult aprroveRegister(HttpServletRequest request,UserVO userVO) {
		User user = new User();
		BeanUtils.copyProperties(userVO, user);
		if(userVO.getIsMarried() != null) {
			if(userVO.getIsMarriedStr().equals("未婚")) {
				user.setIsMarried((byte)1);
			}else {
				user.setIsMarried((byte)2);
			}
		}
		if(userVO.getEducationStr() != null) {
			if(userVO.getEducationStr().equals("专科")) {
				user.setEducation((byte)1);
			}else if(userVO.getEducationStr().equals("本科")) {
				user.setEducation((byte)2);
			}else if(userVO.getEducationStr().equals("硕士")) {
				user.setEducation((byte)3);
			}else {
				user.setEducation((byte)4);
			}
		}
		userService.update(user);
		List<Message> messageLists = messageService.getMessageByUserId(user.getId());
		for(Message m:messageLists) {
			if(m.getType() == 0) {
				m.setIsDone((byte)2);
				messageService.update(m);
				messageService.updateLMessageApprover(1L, m.getId());
				break;
			}
		}
		return CommResult.ok();
		
	}

}
