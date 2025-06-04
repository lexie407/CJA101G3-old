package com.toiukha.forum.article.dto;

import java.sql.Date;
import java.util.List;
import com.toiukha.forum.comments.dto.*;

// 負責包裝資料成回傳的格式
public class ArticleDTO {
    private Integer artId;  //文章編號
    private Byte artCat;	//文章類別
    private Byte artSta;	//文章狀態
    private String mamName; //會員名字
    private Integer artLike;	//文章按讚數
    private String artTitle;   //文章標題
    private String artCon; //文章內容
    private List<CommentsDTO> comments; //文章留言
    private Date artCreTime;	//文章建立時間

    public ArticleDTO() {
        super();
    }

    //無文章留言(測試用)
    public ArticleDTO(Integer artId, Byte artCat, Byte artSta, String mamName, Integer artLike, String artTitle, String artCon, Date artCreTime) {
        this.artId = artId;
        this.artCat = artCat;
        this.artSta = artSta;
        this.mamName = mamName;
        this.artLike = artLike;
        this.artTitle = artTitle;
        this.artCon = artCon;
        this.artCreTime = artCreTime;
    }

    public ArticleDTO(Integer artId, Byte artCat, Byte artSta, String mamName, Integer artLike, String artTitle, String artCon, List<CommentsDTO> comments, Date artCreTime) {
        this.artId = artId;
        this.artCat = artCat;
        this.artSta = artSta;
        this.mamName = mamName;
        this.artLike = artLike;
        this.artTitle = artTitle;
        this.artCon = artCon;
        this.comments = comments;
        this.artCreTime = artCreTime;
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

    public String getMamName() {
        return mamName;
    }

    public void setMamName(String mamName) {
        this.mamName = mamName;
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

    public List<CommentsDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentsDTO> comments) {
        this.comments = comments;
    }

    public Date getArtCreTime() {
        return artCreTime;
    }

    public void setArtCreTime(Date artCreTime) {
        this.artCreTime = artCreTime;
    }

    // 覆寫toString方法，讓它不是回傳物件門牌，而是照我想要的方式
    @Override
    public String  toString() {
        String string = getClass().getSimpleName() + "@" + Integer.toHexString(hashCode());
        string += "[<文章編號> = " + artId + ", <文章類別> = " + artCat + ", <文章狀態> "
                + artSta + ", <會員姓名> " + mamName +  ", <文章標題> " + artTitle + ", <文章內容> = "
                + artCon + ", <文章建立時間> = " + artCreTime + "]";
        return string;
    }
}
