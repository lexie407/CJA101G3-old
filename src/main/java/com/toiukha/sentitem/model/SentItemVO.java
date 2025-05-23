package com.toiukha.sentitem.model;

import java.io.Serializable;

public class SentItemVO implements Serializable{
	
//	private static final long serialVersionUID = 1L;

	private Integer sentItemId;
	private Integer itemId;
	private Integer storeId;
	private Integer memId;
	private Byte itemStatus;
	private Byte scoreMem;
	private String contentMem;
	
	public SentItemVO() {
		super();
	}

	public SentItemVO(Integer sentItemId, Integer itemId, Integer storeId, Integer memId, Byte itemStatus, Byte scoreMem,
			String contentMem) {
		super();
		this.sentItemId = sentItemId;
		this.itemId = itemId;
		this.storeId = storeId;
		this.memId = memId;
		this.itemStatus = itemStatus;
		this.scoreMem = scoreMem;
		this.contentMem = contentMem;
	}

	public Integer getSentItemId() {
		return sentItemId;
	}

	public void setSentItemId(Integer sentItemId) {
		this.sentItemId = sentItemId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	public Byte getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(Byte itemStatus) {
		this.itemStatus = itemStatus;
	}

	public Byte getScoreMem() {
		return scoreMem;
	}

	public void setScoreMem(Byte scoreMem) {
		this.scoreMem = scoreMem;
	}

	public String getContentMem() {
		return contentMem;
	}

	public void setContentMem(String contentMem) {
		this.contentMem = contentMem;
	}
	
	
	
	
	
}
