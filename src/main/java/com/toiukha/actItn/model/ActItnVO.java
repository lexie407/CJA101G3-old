package com.toiukha.actItn.model;

import java.io.Serializable;

public class ActItnVO implements Serializable{
	private Integer actId;
	private Integer itnId;
	
	public ActItnVO() {
		super();
	}
	
	public ActItnVO(Integer actId, Integer itnId) {
		super();
		this.actId = actId;
		this.itnId = itnId;
	}


	public Integer getActId() {
		return actId;
	}
	public void setActId(Integer actId) {
		this.actId = actId;
	}
	public Integer getItnId() {
		return itnId;
	}
	public void setItnId(Integer itnId) {
		this.itnId = itnId;
	}
	

}
