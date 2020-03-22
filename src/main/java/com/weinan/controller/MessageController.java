package com.weinan.controller;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weinan.common.Contant;
import com.weinan.common.result.CommResult;
import com.weinan.common.result.HttpResult;
import com.weinan.common.util.AgeUtils;
import com.weinan.entity.Message;
import com.weinan.entity.User;
import com.weinan.service.MessageService;
import com.weinan.service.UserService;
import com.weinan.vo.MessageVO;
import com.weinan.vo.UserVO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class MessageController {
	
	
	@Resource
	private MessageService messageService;
	
	@Resource
	private UserService userService;
	
	@ApiOperation(value="分页查询所有数据", response=HttpResult.class)
	@ApiResponses({
		@ApiResponse(code=200,message="成功,返回content中vo类参数如下", response=MessageVO.class)
	})
	@RequestMapping("/getMessage")
	//@RequiresPermissions("user:getMessage")
	public HttpResult<MessageVO> getMessage(HttpServletRequest request,@ApiParam(value = "用户Id",required = true)@RequestParam(value = "userId",required = true)Long userId) {
		HttpResult<MessageVO> result = new HttpResult<>();
		List<Message> messageLists = messageService.getMessageByUserId(userId);
		List<MessageVO> realMessageLists = new ArrayList<>();
		for(Message m:messageLists) {
			MessageVO mvo = new MessageVO();
			BeanUtils.copyProperties(m, mvo);
			if(mvo.getCreateTime() != null) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(mvo.getCreateTime());
				int month = cal.get(Calendar.MONTH);
				int day = cal.get(Calendar.DAY_OF_MONTH);
				mvo.setMonth(Contant.MONTH_STR[month]);
				mvo.setDay(day);
			}
			if(mvo.getCreateTime() != null) {
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				mvo.setCreateTimeStr(sf.format(mvo.getCreateTime()));
			}
			if(mvo.getUpdateTime() != null) {
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				mvo.setUpdateTimeStr(sf.format(mvo.getUpdateTime()));
			}
			realMessageLists.add(mvo);
		}
		Long total = new Long(realMessageLists.size());
		result.setData(realMessageLists);
		result.setiTotalRecords(total);
		return result;
	}
	
	@PostMapping("/getUser")
	public CommResult getUser(HttpServletRequest request,@ApiParam(value = "用户Id",required = true)@RequestParam(value = "id",required = true)String id) {
		Long userId = Long.parseLong(id);
		User user = userService.findUserByUserId(userId);
		UserVO userVO = new UserVO();
		BeanUtils.copyProperties(user, userVO);
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
		
		return CommResult.ok(userVO);
		
	}

}
