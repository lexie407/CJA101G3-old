package com.toiukha.managefunction.model;

import java.io.Serializable;

public class ManageFunctionVO implements Serializable {
	private Integer manageFuncId;
	private String manageFuncName;
	
	
	public ManageFunctionVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public ManageFunctionVO(Integer manageFuncId, String manageFuncName) {
		super();
		this.manageFuncId = manageFuncId;
		this.manageFuncName = manageFuncName;
	}


	public Integer getManageFuncId() {
		return manageFuncId;
	}
	public void setManageFuncId(Integer manageFuncId) {
		this.manageFuncId = manageFuncId;
	}
	public String getManageFuncName() {
		return manageFuncName;
	}
	public void setManageFuncName(String manageFuncName) {
		this.manageFuncName = manageFuncName;
	}

}
