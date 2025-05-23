package com.toiukha.advertisment.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class AdvertismentVO implements Serializable{
	
//	private static final long serialVersionUID = 1L;

	private Integer adId;
	private Integer storeId;
	private String adTitle;
	private byte[] adImage;
	private Byte adStatus;
	private Timestamp adCreatedTime;
	private Timestamp adStartTime;
	private Timestamp adEndTime;
	
	public AdvertismentVO() {
		super();
	}

	public AdvertismentVO(Integer adId, Integer storeId, String adTitle, byte[] adImage, Byte adStatus,
			Timestamp adCreatedTime, Timestamp adStartTime, Timestamp adEndTime) {
		super();
		this.adId = adId;
		this.storeId = storeId;
		this.adTitle = adTitle;
		this.adImage = adImage;
		this.adStatus = adStatus;
		this.adCreatedTime = adCreatedTime;
		this.adStartTime = adStartTime;
		this.adEndTime = adEndTime;
	}

	public Integer getAdId() {
		return adId;
	}

	public void setAdId(Integer adId) {
		this.adId = adId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getAdTitle() {
		return adTitle;
	}

	public void setAdTitle(String adTitle) {
		this.adTitle = adTitle;
	}

	public byte[] getAdImage() {
		return adImage;
	}

	public void setAdImage(byte[] adImage) {
		this.adImage = adImage;
	}

	public Byte getAdStatus() {
		return adStatus;
	}

	public void setAdStatus(Byte adStatus) {
		this.adStatus = adStatus;
	}

	public Timestamp getAdCreatedTime() {
		return adCreatedTime;
	}

	public void setAdCreatedTime(Timestamp adCreatedTime) {
		this.adCreatedTime = adCreatedTime;
	}

	public Timestamp getAdStartTime() {
		return adStartTime;
	}

	public void setAdStartTime(Timestamp adStartTime) {
		this.adStartTime = adStartTime;
	}

	public Timestamp getAdEndTime() {
		return adEndTime;
	}

	public void setAdEndTime(Timestamp adEndTime) {
		this.adEndTime = adEndTime;
	}
	
	
	
}
