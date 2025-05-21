package com.toiukha.administrantauth;

import java.io.Serializable;

public class AdministrantAuthVO implements Serializable {
	
	private Integer adminId;
	private Integer manageFuncId;
	
	
	public AdministrantAuthVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public AdministrantAuthVO(Integer adminId, Integer manageFuncId) {
		super();
		this.adminId = adminId;
		this.manageFuncId = manageFuncId;
	}


	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public Integer getManageFuncId() {
		return manageFuncId;
	}
	public void setManageFuncId(Integer manageFuncId) {
		this.manageFuncId = manageFuncId;
	}

}
