package com.toiukha.favitn.model;

import java.io.Serializable;

public class FavItnVO implements Serializable {
//    private static final long serialVersionUID = 1L;

    private Integer favItnId; 
    private Integer memId;    
    
 // 無參數建構子（必要）
    public FavItnVO() {
    }
    
    //get,set
	public Integer getFavItnId() {
		return favItnId;
	}
	public void setFavItnId(Integer favItnId) {
		this.favItnId = favItnId;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
    
    

}
