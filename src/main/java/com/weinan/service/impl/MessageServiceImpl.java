package com.weinan.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.weinan.dao.MessageDao;
import com.weinan.entity.Message;
import com.weinan.entity.User;
import com.weinan.service.MessageService;

@Service("messageService")
public class MessageServiceImpl implements MessageService {
	
	@Resource
	private MessageDao messageDao;

	@Override
	public Long insert(Message message) {
		// TODO Auto-generated method stub
		return messageDao.insert(message);
	}

	@Override
	public List<Message> getMessageByUserId(Long userId) {
		// TODO Auto-generated method stub
		return messageDao.getMessageByUserId(userId);
	}



	@Override
	public int insertApprover(Long messageId, Long approverId) {
		// TODO Auto-generated method stub
		return messageDao.insertApprover(messageId, approverId);
	}

	@Override
	public int update(Message message) {
		// TODO Auto-generated method stub
		return messageDao.update(message);
	}

	@Override
	public int updateLMessageApprover(Long approverId, Long messageId) {
		// TODO Auto-generated method stub
		return messageDao.updateLMessageApprover(approverId, messageId);
	}

}
