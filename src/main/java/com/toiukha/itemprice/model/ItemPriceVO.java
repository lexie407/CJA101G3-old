package com.toiukha.itemprice.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ItemPriceVO implements Serializable{
	
//	private static final long serialVersionUID = 1L;
	
	private Integer itemId;
	private Timestamp startTime;
	private Timestamp endTime;
	private Integer oriPrice;
	
	public ItemPriceVO() {
		super();
	}

	public ItemPriceVO(Integer itemId, Timestamp startTime, Timestamp endTime, Integer oriPrice) {
		super();
		this.itemId = itemId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.oriPrice = oriPrice;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
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

	public Integer getOriPrice() {
		return oriPrice;
	}

	public void setOriPrice(Integer oriPrice) {
		this.oriPrice = oriPrice;
	}

	
	
	
	
}
