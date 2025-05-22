package com.toiukha.administrant.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class AdministrantVO implements Serializable {
	private Integer adminId;
	private String adminAcc;
	private String adminPwd;
	private String adminName;
	private Timestamp adminCreatedAt;
	private Integer adminStatus;
	private Timestamp adminUpdatedAt;
	
	
	
	public AdministrantVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public AdministrantVO(Integer adminId, String adminAcc, String adminPwd, String adminName, Timestamp adminCreatedAt,
			Integer adminStatus, Timestamp adminUpdatedAt) {
		super();
		this.adminId = adminId;
		this.adminAcc = adminAcc;
		this.adminPwd = adminPwd;
		this.adminName = adminName;
		this.adminCreatedAt = adminCreatedAt;
		this.adminStatus = adminStatus;
		this.adminUpdatedAt = adminUpdatedAt;
	}



	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public String getAdminAcc() {
		return adminAcc;
	}
	public void setAdminAcc(String adminAcc) {
		this.adminAcc = adminAcc;
	}
	public String getAdminPwd() {
		return adminPwd;
	}
	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public Timestamp getAdminCreatedAt() {
		return adminCreatedAt;
	}
	public void setAdminCreatedAt(Timestamp adminCreatedAt) {
		this.adminCreatedAt = adminCreatedAt;
	}
	public Integer getAdminStatus() {
		return adminStatus;
	}
	public void setAdminStatus(Integer adminStatus) {
		this.adminStatus = adminStatus;
	}
	public Timestamp getAdminUpdatedAt() {
		return adminUpdatedAt;
	}
	public void setAdminUpdatedAt(Timestamp adminUpdatedAt) {
		this.adminUpdatedAt = adminUpdatedAt;
	}

}
