package com.toiukha.spotimg.model;
import java.io.Serializable;
import java.sql.Timestamp;

public class SpotImgVO implements Serializable {
//	private static final long serialVersionUID = 1L;
	
	private Integer imgId;      
    private Integer spotId;     
    private String imgPath;     
    private String imgDesc;    
    private Timestamp imgTime;
    
    //get,set
	public Integer getImgId() {
		return imgId;
	}
	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}
	public Integer getSpotId() {
		return spotId;
	}
	public void setSpotId(Integer spotId) {
		this.spotId = spotId;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getImgDesc() {
		return imgDesc;
	}
	public void setImgDesc(String imgDesc) {
		this.imgDesc = imgDesc;
	}
	public Timestamp getImgTime() {
		return imgTime;
	}
	public void setImgTime(Timestamp imgTime) {
		this.imgTime = imgTime;
	}  
    
    

}
