package com.toiukha.notification.model;
import java.sql.Date;

public class NotificationVO implements java.io.Serializable {

	private Integer notiId;
	private String notiTitle;
	private String notiCont;
	private Date notiCreatedAt;
	private Integer memId;
	private Integer notiStatus;
	private Integer adminId;
	private Date notiUpdatedAt;
	private Date notiSendAt;
	
	public NotificationVO() {
		super();
	}

	public Integer getNotiId() {
		return notiId;
	}

	public void setNotiId(Integer notiId) {
		this.notiId = notiId;
	}

	public String getNotiTitle() {
		return notiTitle;
	}

	public void setNotiTitle(String notiTitle) {
		this.notiTitle = notiTitle;
	}

	public String getNotiCont() {
		return notiCont;
	}

	public void setNotiCont(String notiCont) {
		this.notiCont = notiCont;
	}

	public Date getNotiCreatedAt() {
		return notiCreatedAt;
	}

	public void setNotiCreatedAt(Date notiCreatedAt) {
		this.notiCreatedAt = notiCreatedAt;
	}

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	public Integer getNotiStatus() {
		return notiStatus;
	}

	public void setNotiStatus(Integer notiStatus) {
		this.notiStatus = notiStatus;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public Date getNotiUpdatedAt() {
		return notiUpdatedAt;
	}

	public void setNotiUpdatedAt(Date notiUpdatedAt) {
		this.notiUpdatedAt = notiUpdatedAt;
	}

	public Date getNotiSendAt() {
		return notiSendAt;
	}

	public void setNotiSendAt(Date notiSendAt) {
		this.notiSendAt = notiSendAt;
	}
	
	
	
	
	
}
