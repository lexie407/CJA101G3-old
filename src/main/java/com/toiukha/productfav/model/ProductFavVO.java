package com.toiukha.productfav.model;

import java.sql.Timestamp;

public class ProductFavVO implements java.io.Serializable {
	private Integer memId;
	private Integer itemId;
	private Timestamp favAt;

	public ProductFavVO() {
	}

	public ProductFavVO(Integer memId, Integer itemId, Timestamp favAt) {
		super();
		this.memId = memId;
		this.itemId = itemId;
		this.favAt = favAt;
	}

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Timestamp getFavAt() {
		return favAt;
	}

	public void setFavAt(Timestamp favAt) {
		this.favAt = favAt;
	}
}
