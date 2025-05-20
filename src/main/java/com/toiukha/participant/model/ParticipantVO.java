package com.toiukha.participant.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ParticipantVO implements Serializable{
	private Integer actId;
	private Integer memId;
	private String role;
	private Timestamp joinTime;
	private Byte joinStatus;
	
	
	public ParticipantVO() {
		super();
	}
	
	public ParticipantVO(Integer actId, Integer memId, String role, Timestamp joinTime, Byte joinStatus) {
		super();
		this.actId = actId;
		this.memId = memId;
		this.role = role;
		this.joinTime = joinTime;
		this.joinStatus = joinStatus;
	}


	public Integer getActId() {
		return actId;
	}
	public void setActId(Integer actId) {
		this.actId = actId;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Timestamp getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(Timestamp joinTime) {
		this.joinTime = joinTime;
	}
	public Byte getJoinStatus() {
		return joinStatus;
	}
	public void setJoinStatus(Byte joinStatus) {
		this.joinStatus = joinStatus;
	}

}
