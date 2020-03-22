package com.weinan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.weinan.entity.Message;
import com.weinan.entity.User;

@Repository
public interface MessageDao {
	
	public Long insert(Message message);
	public List<Message> getMessageByUserId(Long userId);
	public List<User> getApprover(Long messageId);
	public List<Message> getMessageByApproverId(Long appoverId);
	public int insertApprover(@Param("messageId")Long messageId,@Param("approverId")Long approverId);
	public int update(Message message);
	public int updateLMessageApprover(@Param("approverId")Long approverId,@Param("messageId")Long messageId);

}
