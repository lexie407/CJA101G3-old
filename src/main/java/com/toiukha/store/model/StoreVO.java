package com.toiukha.store.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class StoreVO implements Serializable {
	private Integer storeID;
	private String storeAcc;
	private String storePwd;
	private String storeName;
	private String storeGui;
	private String storeRep;
	private String storeTel;
	private String storeAddr;
	private String storeFax;
	private String storeEmail;
	private Timestamp storeRegDate;
	private Timestamp storeUpdatedAt;
	private Integer storeStatus;
	private byte[] storeImg;
	
	
	public StoreVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public StoreVO(Integer storeID, String storeAcc, String storePwd, String storeName, String storeGui,
			String storeRep, String storeTel, String storeAddr, String storeFax, String storeEmail,
			Timestamp storeRegDate, Timestamp storeUpdatedAt, Integer storeStatus, byte[] storeImg) {
		super();
		this.storeID = storeID;
		this.storeAcc = storeAcc;
		this.storePwd = storePwd;
		this.storeName = storeName;
		this.storeGui = storeGui;
		this.storeRep = storeRep;
		this.storeTel = storeTel;
		this.storeAddr = storeAddr;
		this.storeFax = storeFax;
		this.storeEmail = storeEmail;
		this.storeRegDate = storeRegDate;
		this.storeUpdatedAt = storeUpdatedAt;
		this.storeStatus = storeStatus;
		this.storeImg = storeImg;
	}




	public Integer getStoreID() {
		return storeID;
	}
	public void setStoreID(Integer storeID) {
		this.storeID = storeID;
	}
	public String getStoreAcc() {
		return storeAcc;
	}
	public void setStoreAcc(String storeAcc) {
		this.storeAcc = storeAcc;
	}
	public String getStorePwd() {
		return storePwd;
	}
	public void setStorePwd(String storePwd) {
		this.storePwd = storePwd;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreGui() {
		return storeGui;
	}
	public void setStoreGui(String storeGui) {
		this.storeGui = storeGui;
	}
	public String getStoreRep() {
		return storeRep;
	}
	public void setStoreRep(String storeRep) {
		this.storeRep = storeRep;
	}
	public String getStoreTel() {
		return storeTel;
	}
	public void setStoreTel(String storeTel) {
		this.storeTel = storeTel;
	}
	public String getStoreAddr() {
		return storeAddr;
	}
	public void setStoreAddr(String storeAddr) {
		this.storeAddr = storeAddr;
	}
	public String getStoreFax() {
		return storeFax;
	}
	public void setStoreFax(String storeFax) {
		this.storeFax = storeFax;
	}
	public String getStoreEmail() {
		return storeEmail;
	}
	public void setStoreEmail(String storeEmail) {
		this.storeEmail = storeEmail;
	}
	public Timestamp getStoreRegDate() {
		return storeRegDate;
	}
	public void setStoreRegDate(Timestamp storeRegDate) {
		this.storeRegDate = storeRegDate;
	}
	public Timestamp getStoreUpdatedAt() {
		return storeUpdatedAt;
	}
	public void setStoreUpdatedAt(Timestamp storeUpdatedAt) {
		this.storeUpdatedAt = storeUpdatedAt;
	}
	public Integer getStoreStatus() {
		return storeStatus;
	}
	public void setStoreStatus(Integer storeStatus) {
		this.storeStatus = storeStatus;
	}
	public byte[] getStoreImg() {
		return storeImg;
	}
	public void setStoreImg(byte[] storeImg) {
		this.storeImg = storeImg;
	}
	
	
}
