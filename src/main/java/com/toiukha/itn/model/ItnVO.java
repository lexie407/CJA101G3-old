package com.toiukha.itn.model;
import java.io.Serializable;

public class ItnVO implements Serializable {
//	private static final long serialVersionUID = 1L;

	private Integer itnId;
	private String itnName;
	private Integer crtId;
	private String itnDesc;
	private Byte isPublic;
	private Byte itnStatus;
	
    // 無參數建構子
    public ItnVO() {
    }
    
	//get,set
	public Integer getItnId() {
		return itnId;
	}
	public void setItnId(Integer itnId) {
		this.itnId = itnId;
	}
	public String getItnName() {
		return itnName;
	}
	public void setItnName(String itnName) {
		this.itnName = itnName;
	}
	public Integer getCrtId() {
		return crtId;
	}
	public void setCrtId(Integer crtId) {
		this.crtId = crtId;
	}
	public String getItnDesc() {
		return itnDesc;
	}
	public void setItnDesc(String itnDesc) {
		this.itnDesc = itnDesc;
	}
	public Byte getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(Byte isPublic) {
		this.isPublic = isPublic;
	}
	public Byte getItnStatus() {
		return itnStatus;
	}
	public void setItnStatus(Byte itnStatus) {
		this.itnStatus = itnStatus;
	}

	
}