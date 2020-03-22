package com.weinan.entity;

import java.util.Date;
import java.util.List;

import com.weinan.common.entity.BasicEntity;

public class Message extends BasicEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;//id
	private Long userId;//发起人Id
	private String event;//事件描述
	private Byte type;//事件类型
	private Date createTime;//创建时间
	private Byte isDone;//是否已完成(0:未完成,1:加急处理,2:已完成)
	private Date updateTime;//更改时间
	private Long transactionId;//事务Id
	private List<User> approvers;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public Byte getType() {
		return type;
	}
	public void setType(Byte type) {
		this.type = type;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Byte getIsDone() {
		return isDone;
	}
	public void setIsDone(Byte isDone) {
		this.isDone = isDone;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public List<User> getApprovers() {
		return approvers;
	}
	public void setApprovers(List<User> approvers) {
		this.approvers = approvers;
	}

}
