package com.toiukha.members.model;
	
	import java.io.Serializable;
	import java.sql.Date;
	import java.sql.Timestamp;

	public class MembersVO implements Serializable {
		private Integer memId;
		private String memAcc;
		private String memPwd;
		private String memName;
		private String memGender;
		private Date memBirthDate;
		private String memMobile;
		private String memEmail;
		private String memAddr;
		private Timestamp memRegTime;
		private Integer memPoint;
		private Timestamp memUpdatedAt;
		private Integer memStatus;
		private byte[] memAvatar;
		private String memUsername;
		private byte[] memAvatarFrame;
		private Integer memLogErrCount;
		private Timestamp memLogErrTime;
		private Integer memGroupAuth;
		private Integer memGroupPoint;
		private Integer memStoreAuth;
		private Integer memStorePoint;
		
		
		
		public MembersVO() {
			super();
		}
		
		

		public MembersVO(Integer memId, String memAcc, String memPwd, String memName, String memGender, Date memBirthDate,
				String memMobile, String memEmail, String memAddr, Timestamp memRegTime, Integer memPoint,
				Timestamp memUpdatedAt, Integer memStatus, byte[] memAvatar, String memUsername, byte[] memAvatarFrame,
				Integer memLogErrCount, Timestamp memLogErrTime, Integer memGroupAuth, Integer memGroupPoint,
				Integer memStoreAuth, Integer memStorePoint) {
			super();
			this.memId = memId;
			this.memAcc = memAcc;
			this.memPwd = memPwd;
			this.memName = memName;
			this.memGender = memGender;
			this.memBirthDate = memBirthDate;
			this.memMobile = memMobile;
			this.memEmail = memEmail;
			this.memAddr = memAddr;
			this.memRegTime = memRegTime;
			this.memPoint = memPoint;
			this.memUpdatedAt = memUpdatedAt;
			this.memStatus = memStatus;
			this.memAvatar = memAvatar;
			this.memUsername = memUsername;
			this.memAvatarFrame = memAvatarFrame;
			this.memLogErrCount = memLogErrCount;
			this.memLogErrTime = memLogErrTime;
			this.memGroupAuth = memGroupAuth;
			this.memGroupPoint = memGroupPoint;
			this.memStoreAuth = memStoreAuth;
			this.memStorePoint = memStorePoint;
		}


		public Integer getMemId() {
			return memId;
		}
		
		public void setMemId(Integer memId) {
			this.memId = memId;
		}
		
		public String getMemAcc() {
			return memAcc;
		}
		
		public void setMemAcc(String memAcc) {
			this.memAcc = memAcc;
		}
		
		public String getMemPwd() {
			return memPwd;
		}
		
		public void setMemPwd(String memPwd) {
			this.memPwd = memPwd;
		}
		
		public String getMemName() {
			return memName;
		}
		
		public void setMemName(String memName) {
			this.memName = memName;
		}
		
		public String getMemGender() {
			return memGender;
		}
		
		public void setMemGender(String memGender) {
			this.memGender = memGender;
		}
		
		public Date getMemBirthDate() {
			return memBirthDate;
		}
		
		public void setMemBirthDate(Date memBirthDate) {
			this.memBirthDate = memBirthDate;
		}
		
		public String getMemMobile() {
			return memMobile;
		}
		
		public void setMemMobile(String memMobile) {
			this.memMobile = memMobile;
		}
		
		public String getMemEmail() {
			return memEmail;
		}
		
		public void setMemEmail(String memEmail) {
			this.memEmail = memEmail;
		}
		
		public String getMemAddr() {
			return memAddr;
		}
		
		public void setMemAddr(String memAddr) {
			this.memAddr = memAddr;
		}
		
		public Timestamp getMemRegTime() {
			return memRegTime;
		}
		
		public void setMemRegTime(Timestamp memRegTime) {
			this.memRegTime = memRegTime;
		}
		
		public Integer getMemPoint() {
			return memPoint;
		}
		
		public void setMemPoint(Integer memPoint) {
			this.memPoint = memPoint;
		}
		
		public Timestamp getMemUpdatedAt() {
			return memUpdatedAt;
		}
		
		public void setMemUpdatedAt(Timestamp memUpdatedAt) {
			this.memUpdatedAt = memUpdatedAt;
		}
		
		public Integer getMemStatus() {
			return memStatus;
		}
		
		public void setMemStatus(Integer memStatus) {
			this.memStatus = memStatus;
		}
		
		public byte[] getMemAvatar() {
			return memAvatar;
		}
		
		public void setMemAvatar(byte[] memAvatar) {
			this.memAvatar = memAvatar;
		}
		
		public String getMemUsername() {
			return memUsername;
		}
		
		public void setMemUsername(String memUsername) {
			this.memUsername = memUsername;
		}
		
		public byte[] getMemAvatarFrame() {
			return memAvatarFrame;
		}
		
		public void setMemAvatarFrame(byte[] memAvatarFrame) {
			this.memAvatarFrame = memAvatarFrame;
		}
		
		public Integer getMemLogErrCount() {
			return memLogErrCount;
		}
		
		public void setMemLogErrCount(Integer memLogErrCount) {
			this.memLogErrCount = memLogErrCount;
		}
		
		public Timestamp getMemLogErrTime() {
			return memLogErrTime;
		}
		
		public void setMemLogErrTime(Timestamp memLogErrTime) {
			this.memLogErrTime = memLogErrTime;
		}
		
		public Integer getMemGroupAuth() {
			return memGroupAuth;
		}
		
		public void setMemGroupAuth(Integer memGroupAuth) {
			this.memGroupAuth = memGroupAuth;
		}
		
		public Integer getMemGroupPoint() {
			return memGroupPoint;
		}
		
		public void setMemGroupPoint(Integer memGroupPoint) {
			this.memGroupPoint = memGroupPoint;
		}
		
		public Integer getMemStoreAuth() {
			return memStoreAuth;
		}
		
		public void setMemStoreAuth(Integer memStoreAuth) {
			this.memStoreAuth = memStoreAuth;
		}
		
		public Integer getMemStorePoint() {
			return memStorePoint;
		}
		
		public void setMemStorePoint(Integer memStorePoint) {
			this.memStorePoint = memStorePoint;
		}
		
		
		
	

}
