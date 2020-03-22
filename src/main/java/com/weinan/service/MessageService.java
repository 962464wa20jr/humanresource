package com.weinan.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.weinan.entity.Message;
import com.weinan.entity.User;

public interface MessageService {
	public Long insert(Message message);
	public List<Message> getMessageByUserId(Long userId);
	//public List<User> getApprover(Long messageId);
	public int insertApprover(@Param("messageId")Long messageId,@Param("approverId")Long approverId);
	public int update(Message message);
	public int updateLMessageApprover(Long approverId,Long messageId);


}
