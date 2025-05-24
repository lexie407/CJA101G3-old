package com.toiukha.rptitn.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class RptItnVO implements Serializable {
//    private static final long serialVersionUID = 1L;

    private Integer rptId;         
    private Integer tgtItnId;      
    private Integer rprId;         
    private String rptDesc;        
    private Timestamp rptTime;     
    private Byte rptStatus;       
    private Timestamp rptProcTime; 
    private Integer adminId;
    
    // 無參數建構子
    public RptItnVO() {
    	super();
    }
    
    //set,get
	public Integer getRptId() {
		return rptId;
	}
	public void setRptId(Integer rptId) {
		this.rptId = rptId;
	}
	public Integer getTgtItnId() {
		return tgtItnId;
	}
	public void setTgtItnId(Integer tgtItnId) {
		this.tgtItnId = tgtItnId;
	}
	public Integer getRprId() {
		return rprId;
	}
	public void setRprId(Integer rprId) {
		this.rprId = rprId;
	}
	public String getRptDesc() {
		return rptDesc;
	}
	public void setRptDesc(String rptDesc) {
		this.rptDesc = rptDesc;
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
