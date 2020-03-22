package com.weinan.entity;

import java.util.Date;

import com.weinan.common.entity.BasicEntity;

public class RegisterTransaction extends BasicEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long applyId;//申请人id
	private Long approverId;//审批人Id
	private Date createTime;//创建时间
	private Date updateTime;//更改时间
	private Byte status;//审批状态
	public Long getApplyId() {
		return applyId;
	}
	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}
	public Long getApproverId() {
		return approverId;
	}
	public void setApproverId(Long approverId) {
		this.approverId = approverId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
