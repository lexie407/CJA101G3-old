package com.toiukha.reportact.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ReportActVO implements Serializable{
	private Integer rptId;
	private Integer tgtActId;
	private Integer rprId;
	private String rprDesc;
	private Timestamp rptTime;
	private Byte rptStatus;
	private Timestamp rptProcTime;
	private Integer adminId;
	
	
	public ReportActVO() {
		super();
	}
	
	public ReportActVO(Integer rptId, Integer tgtActId, Integer rprId, String rprDesc, Timestamp rptTime,
			Byte rptStatus, Timestamp rptProcTime, Integer adminId) {
		super();
		this.rptId = rptId;
		this.tgtActId = tgtActId;
		this.rprId = rprId;
		this.rprDesc = rprDesc;
		this.rptTime = rptTime;
		this.rptStatus = rptStatus;
		this.rptProcTime = rptProcTime;
		this.adminId = adminId;
	}


	public Integer getRptId() {
		return rptId;
	}
	public void setRptId(Integer rptId) {
		this.rptId = rptId;
	}
	public Integer getTgtActId() {
		return tgtActId;
	}
	public void setTgtActId(Integer tgtActId) {
		this.tgtActId = tgtActId;
	}
	public Integer getRprId() {
		return rprId;
	}
	public void setRprId(Integer rprId) {
		this.rprId = rprId;
	}
	public String getRprDesc() {
		return rprDesc;
	}
	public void setRprDesc(String rprDesc) {
		this.rprDesc = rprDesc;
	}
	public Timestamp getRptTime() {
		return rptTime;
	}
	public void setRptTime(Timestamp rptTime) {
		this.rptTime = rptTime;
	}
	public Byte getRptStatus() {
		return rptStatus;
	}
	public void setRptStatus(Byte rptStatus) {
		this.rptStatus = rptStatus;
	}
	public Timestamp getRptProcTime() {
		return rptProcTime;
	}
	public void setRptProcTime(Timestamp rptProcTime) {
		this.rptProcTime = rptProcTime;
	}
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	
}
