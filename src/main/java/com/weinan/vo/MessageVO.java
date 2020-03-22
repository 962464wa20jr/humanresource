package com.weinan.vo;



import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.weinan.common.entity.BasicEntity;
import com.weinan.entity.User;

public class MessageVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String event;//事件描述
	private Long userId;//发起人Id
	private Byte type;//事件类型
	private Date createTime;//创建时间
	private String createTimeStr;//格式化
	private String month;//月份
	private int day;//日期
	private Byte isDone;//是否完成
	private Date updateTime;//更改时间
	private String updateTimeStr;//格式化
	private Long transactionId;//事务Id
	private List<User> approvers;
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
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
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
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
	public String getUpdateTimeStr() {
		return updateTimeStr;
	}
	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}


}
