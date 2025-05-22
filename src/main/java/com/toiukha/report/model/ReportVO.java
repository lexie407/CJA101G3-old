package com.toiukha.report.model;
import java.sql.Date;

public class ReportVO implements java.io.Serializable {

//	可能改為redis寫，先預備著
	private Integer repId;
	private Integer memId;
	private String repType;
	private String repDesc;
	private Integer repStatus;
	private Date repAt;
	private byte[] repImg;
	
	public ReportVO() {
		super();
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

	public String getRepType() {
		return repType;
	}

	public void setRepType(String repType) {
		this.repType = repType;
	}

	public String getRepDesc() {
		return repDesc;
	}

	public void setRepDesc(String repDesc) {
		this.repDesc = repDesc;
	}

	public Integer getRepStatus() {
		return repStatus;
	}

	public void setRepStatus(Integer repStatus) {
		this.repStatus = repStatus;
	}

	public Date getRepAt() {
		return repAt;
	}

	public void setRepAt(Date repAt) {
		this.repAt = repAt;
	}

	public byte[] getRepImg() {
		return repImg;
	}

	public void setRepImg(byte[] repImg) {
		this.repImg = repImg;
	}
	
}
