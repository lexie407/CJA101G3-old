package com.toiukha.coupon.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CouponVO implements Serializable{
	
//	private static final long serialVersionUID = 1L;
	
	private Integer couId;
	private Integer storeId;
	private Timestamp startTime;
	private Timestamp endTime;
	private Integer discValue;
	private String couName;
	
	
	public CouponVO() {
		super();
	}


	public CouponVO(Integer couId, Integer storeId, Timestamp startTime, Timestamp endTime, Integer discValue,
			String couName) {
		super();
		this.couId = couId;
		this.storeId = storeId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.discValue = discValue;
		this.couName = couName;
	}


	public Integer getCouId() {
		return couId;
	}


	public void setCouId(Integer couId) {
		this.couId = couId;
	}


	public Integer getStoId() {
		return storeId;
	}


	public void setStoId(Integer stoId) {
		this.storeId = stoId;
	}


	public Timestamp getStartTime() {
		return startTime;
	}


	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}


	public Timestamp getEndTime() {
		return endTime;
	}


	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}


	public Integer getDiscValue() {
		return discValue;
	}


	public void setDiscValue(Integer discValue) {
		this.discValue = discValue;
	}


	public String getCouName() {
		return couName;
	}


	public void setCouName(String couName) {
		this.couName = couName;
	}
	
	
}
