package com.toiukha.promo.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class PromoVO implements Serializable{
	
//	private static final long serialVersionUID = 1L;

	private Integer proId;
	private Integer storeId;
	private String proTit;
	private Timestamp staTime;
	private Timestamp endTime;
	
	public PromoVO() {
		super();	
	}

	public PromoVO(Integer proId, Integer storeId, String proTit, Timestamp staTime, Timestamp endTime) {
		super();
		this.proId = proId;
		this.storeId = storeId;
		this.proTit = proTit;
		this.staTime = staTime;
		this.endTime = endTime;
	}

	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	public Integer getStoId() {
		return storeId;
	}

	public void setStoId(Integer stoId) {
		this.storeId = stoId;
	}

	public String getProTit() {
		return proTit;
	}

	public void setProTit(String proTit) {
		this.proTit = proTit;
	}

	public Timestamp getStaTime() {
		return staTime;
	}

	public void setStaTime(Timestamp staTime) {
		this.staTime = staTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	
	
}
