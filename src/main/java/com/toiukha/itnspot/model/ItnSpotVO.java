package com.toiukha.itnspot.model;

import java.io.Serializable;

public class ItnSpotVO implements Serializable {
//    private static final long serialVersionUID = 1L;

    private Integer itnId;   
    private Integer spotId;
    
    //set,get
	public Integer getItnId() {
		return itnId;
	}
	public void setItnId(Integer itnId) {
		this.itnId = itnId;
	}
	public Integer getSpotId() {
		return spotId;
	}
	public void setSpotId(Integer spotId) {
		this.spotId = spotId;
	}  
    
    

}
