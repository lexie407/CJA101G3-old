package com.toiukha.memreport.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "memreport")
public class MemReportVO implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "repid")
	private Integer repId;

	@ManyToOne
	@JoinColumn(name = "memid", nullable = false)
	private Integer memId;

	@ManyToOne
	@JoinColumn(name = "ordid")
	private Integer ordId;

	@ManyToOne
	@JoinColumn(name = "itemid")
	private Integer itemId;

	@Column(name = "repreason", nullable = false, length = 200)
	private String repReason;

	@Column(name = "repstatus", nullable = false)
	private Integer repStatus;

	@Column(name = "repat", nullable = false)
	private Timestamp repAt;

	@Column(name = "repimg", columnDefinition = "longblob")
	private byte[] repImg;

	@ManyToOne
	@JoinColumn(name = "adminid")
	private Integer adminId;

	@Column(name = "rptproctime")
	private Timestamp rptProcTime;

	public MemReportVO() {
		super();
	}

	public MemReportVO(Integer repId, Integer memId, Integer ordId, Integer itemId, String repReason, Integer repStatus,
			Timestamp repAt, byte[] repImg, Integer adminId, Timestamp rptProcTime) {
		super();
		this.repId = repId;
		this.memId = memId;
		this.ordId = ordId;
		this.itemId = itemId;
		this.repReason = repReason;
		this.repStatus = repStatus;
		this.repAt = repAt;
		this.repImg = repImg;
		this.adminId = adminId;
		this.rptProcTime = rptProcTime;
	}

	public Integer getRepId() {
		return repId;
	}

	public void setRepId(Integer repId) {
		this.repId = repId;
	}

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	public Integer getOrdId() {
		return ordId;
	}

	public void setOrdId(Integer ordId) {
		this.ordId = ordId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getRepReason() {
		return repReason;
	}

	public void setRepReason(String repReason) {
		this.repReason = repReason;
	}

	public Integer getRepStatus() {
		return repStatus;
	}

	public void setRepStatus(Integer repStatus) {
		this.repStatus = repStatus;
	}

	public Timestamp getRepAt() {
		return repAt;
	}

	public void setRepAt(Timestamp repAt) {
		this.repAt = repAt;
	}

	public byte[] getRepImg() {
		return repImg;
	}

	public void setRepImg(byte[] repImg) {
		this.repImg = repImg;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public Timestamp getRptProcTime() {
		return rptProcTime;
	}

	public void setRptProcTime(Timestamp rptProcTime) {
		this.rptProcTime = rptProcTime;
	}

}
