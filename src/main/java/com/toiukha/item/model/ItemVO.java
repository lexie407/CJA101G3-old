package com.toiukha.item.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ItemVO implements Serializable{
	
//	private static final long serialVersionUID = 1L;

	private Integer itemId;
	private Integer storeId;
	private String itemName ;
	private Integer stockQuantity;
	private Byte itemStatus;
	private Timestamp creAt;
	private Timestamp updAt;
	private byte[] itemImg;
	private Integer repCount;
	
	
	public ItemVO() {
		super();
	}
	
	public ItemVO(Integer itemId, Integer storeId, String itemName, Integer stockQuantity, Byte itemStatus,
			Timestamp creAt, Timestamp updAt, byte[] itemImg, Integer repCount) {
		super();
		this.itemId = itemId;
		this.storeId = storeId;
		this.itemName = itemName;
		this.stockQuantity = stockQuantity;
		this.itemStatus = itemStatus;
		this.creAt = creAt;
		this.updAt = updAt;
		this.itemImg = itemImg;
		this.repCount = repCount;
	}
	
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Integer getStoId() {
		return storeId;
	}
	public void setStoId(Integer stoId) {
		this.storeId = stoId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Integer getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	public Byte getItemStatus() {
		return itemStatus;
	}
	public void setItemStatus(Byte itemStatus) {
		this.itemStatus = itemStatus;
	}
	public Timestamp getCreAt() {
		return creAt;
	}
	public void setCreAt(Timestamp creAt) {
		this.creAt = creAt;
	}
	public Timestamp getUpdAt() {
		return updAt;
	}
	public void setUpdAt(Timestamp updAt) {
		this.updAt = updAt;
	}
	public byte[] getItemImg() {
		return itemImg;
	}
	public void setItemImg(byte[] itemImg) {
		this.itemImg = itemImg;
	}
	public Integer getRepCount() {
		return repCount;
	}
	public void setRepCount(Integer repCount) {
		this.repCount = repCount;
	}

}
