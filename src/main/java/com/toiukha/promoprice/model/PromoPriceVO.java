package com.toiukha.promoprice.model;

import java.io.Serializable;

public class PromoPriceVO implements Serializable{

//	private static final long serialVersionUID = 1L;

	private Integer promoPriceId;
	private Integer itemId;
	private Integer proId;
	private Integer discPrice;
	
	public PromoPriceVO() {
		super();
	}

	public PromoPriceVO(Integer promoPriceId, Integer itemId, Integer proId, Integer discPrice) {
		super();
		this.promoPriceId = promoPriceId;
		this.itemId = itemId;
		this.proId = proId;
		this.discPrice = discPrice;
	}

	public Integer getPromoPriceId() {
		return promoPriceId;
	}

	public void setPromoPriceId(Integer promoPriceId) {
		this.promoPriceId = promoPriceId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	public Integer getDiscPrice() {
		return discPrice;
	}

	public void setDiscPrice(Integer discPrice) {
		this.discPrice = discPrice;
	}
		
	
	
}
