package com.toiukha.spot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "spot")
public class SpotVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SPOTID")
	private Integer spotId;

	@Column(name = "SPOTNAME", nullable = false, length = 100)
	private String spotName;

	@Column(name = "CRTID")
	private Integer crtId;

	@Column(name = "SPOTLOC", length = 255)
	private String spotLoc;

	@Column(name = "SPOTLAT")
	private Double spotLat;

	@Column(name = "SPOTLNG")
	private Double spotLng;

	@Column(name = "SPOTSTATUS")
	private Byte spotStatus;

	@Column(name = "SPOTDESC", length = 500)
	private String spotDesc;

	// 無參數建構子
	public SpotVO() {
	}

	// 帶參數建構子
	public SpotVO(String spotName, Integer crtId, String spotLoc, Double spotLat, Double spotLng, Byte spotStatus,
			String spotDesc) {
		this.spotName = spotName;
		this.crtId = crtId;
		this.spotLoc = spotLoc;
		this.spotLat = spotLat;
		this.spotLng = spotLng;
		this.spotStatus = spotStatus;
		this.spotDesc = spotDesc;
	}

	// Getter 和 Setter 方法
	public Integer getSpotId() {
		return spotId;
	}

	public void setSpotId(Integer spotId) {
		this.spotId = spotId;
	}

	public String getSpotName() {
		return spotName;
	}

	public void setSpotName(String spotName) {
		this.spotName = spotName;
	}

	public Integer getCrtId() {
		return crtId;
	}

	public void setCrtId(Integer crtId) {
		this.crtId = crtId;
	}

	public String getSpotLoc() {
		return spotLoc;
	}

	public void setSpotLoc(String spotLoc) {
		this.spotLoc = spotLoc;
	}

	public Double getSpotLat() {
		return spotLat;
	}

	public void setSpotLat(Double spotLat) {
		this.spotLat = spotLat;
	}

	public Double getSpotLng() {
		return spotLng;
	}

	public void setSpotLng(Double spotLng) {
		this.spotLng = spotLng;
	}

	public Byte getSpotStatus() {
		return spotStatus;
	}

	public void setSpotStatus(Byte spotStatus) {
		this.spotStatus = spotStatus;
	}

	public String getSpotDesc() {
		return spotDesc;
	}

	public void setSpotDesc(String spotDesc) {
		this.spotDesc = spotDesc;
	}
}
