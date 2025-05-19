package com.toiukha.forum.article.entity;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "article")
public class ArticleVO implements Serializable{
	
	private static final long serialVersionUID = -1848561202771945566L; //序列化判斷用
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ARTID", updatable = false)
	private Integer artId;
	
	@Column(name = "ARTCAT", nullable = false)
	private Byte artCat;
	
	@Column(name = "ARTSTA", nullable = false)
	private Byte artSta;
	
	@Column(name = "ARTHOL", nullable = false)
	private Integer artHol;
	
	@Column(name = "ARTLIKE")
	private Integer artLike;
	
	@Column(name = "ARTTITLE")
	private String artTitle;
	
	//可能試著改用圖片轉base64的做法
	@Column(name = "ARTCON")
	private String artCon;
	
	@Column(name = "ARTCRETIME")
	private Date artCreTime;

	public ArticleVO(Integer artId, Byte artCat, Byte artSta, Integer artHol, Integer artLike, String artTitle,
			String artCon, Date artCreTime) {
		super();
		this.artId = artId;
		this.artCat = artCat;
		this.artSta = artSta;
		this.artHol = artHol;
		this.artLike = artLike;
		this.artTitle = artTitle;
		this.artCon = artCon;
		this.artCreTime = artCreTime;
	}

	public ArticleVO() {
		super();
	}

	public Integer getArtId() {
		return artId;
	}

	public void setArtId(Integer artId) {
		this.artId = artId;
	}

	public Byte getArtCat() {
		return artCat;
	}

	public void setArtCat(Byte artCat) {
		this.artCat = artCat;
	}

	public Byte getArtSta() {
		return artSta;
	}

	public void setArtSta(Byte artSta) {
		this.artSta = artSta;
	}

	public Integer getArtHol() {
		return artHol;
	}

	public void setArtHol(Integer artHol) {
		this.artHol = artHol;
	}

	public Integer getArtLike() {
		return artLike;
	}

	public void setArtLike(Integer artLike) {
		this.artLike = artLike;
	}

	public String getArtTitle() {
		return artTitle;
	}

	public void setArtTitle(String artTitle) {
		this.artTitle = artTitle;
	}

	public String getArtCon() {
		return artCon;
	}

	public void setArtCon(String artCon) {
		this.artCon = artCon;
	}

	public Date getArtCreTime() {
		return artCreTime;
	}

	public void setArtCreTime(Date artCreTime) {
		this.artCreTime = artCreTime;
	}

}
