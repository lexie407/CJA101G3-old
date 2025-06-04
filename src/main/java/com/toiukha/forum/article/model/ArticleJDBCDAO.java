package com.toiukha.forum.article.model;

import com.toiukha.forum.article.dto.ArticleDTO;
import com.toiukha.forum.article.entity.ArticleVO;
import com.toiukha.forum.util.Debug;
import com.toiukha.forum.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleJDBCDAO implements IArticleDAO {
    /* TODO: 新增修改刪除的定義
     *   最小MVP：新增、修改、瀏覽全部文章V、瀏覽單一文章（附帶留言） */
    private static final String GET_ALL = "SELECT * FROM article";
    private static final String GET_ONE_BYARTID = "SELECT * FROM article WHERE ARTID = ?";
    private static final String INSERT = "INSERT INTO article (ARTCAT,ARTSTA,ARTHOL,ARTTITLE,ARTCON) VALUES (? , ? , ? , ? , ? )";
    private static final String DELETE = "DELETE FROM article WHERE ARTID = ?";
    private static final String UPDATE_ALL = "UPDATE article SET ARTCAT = ?, ARTSTA = ?, ARTHOL = ?, ARTLIKE = ?, ARTTITLE = ?, ARTCON = ? ,ARTCRETIME = ? WHERE ARTID = ?";
    private static final String UPDATE = "UPDATE article SET ARTCAT = ?, ARTSTA = ?, ARTTITLE = ?, ARTCON = ? WHERE ARTID = ?";

    private static final String GET_DTO_BYARTID = "SELECT ARTID, ARTCAT, ARTSTA, m.MEMNAME as MAMNAME, ARTLIKE, ARTTITLE, ARTCON, ARTCRETIME FROM article a LEFT OUTER JOIN toiukha.members m on m.MEMID = a.ARTHOL WHERE ARTID = ?";
    private static final String GET_ALLDTO = "SELECT ARTID, ARTCAT, ARTSTA, m.MEMNAME as MAMNAME, ARTLIKE, ARTTITLE, ARTCON, ARTCRETIME FROM article a LEFT OUTER JOIN toiukha.members m on m.MEMID = a.ARTHOL";

    // 當類別載入時會執行這一個區塊，叫JVM載入放著驅動資訊的JDBCUtil
    static {
        try {
            Class.forName(JDBCUtil.DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // 新增文章
    @Override
    public void insert(ArticleVO article) {
        Debug.log("ArtcleDAOInsert方法");
        //創建資源
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = JDBCUtil.getConnection(); //建立連線

            // 設定SQL語法
            ps = con.prepareStatement(INSERT);
            // 設定參數
            ps.setByte(1, article.getArtCat());
            ps.setByte(2, article.getArtSta());
            ps.setInt(3,article.getArtHol());
            ps.setString(4, article.getArtTitle());
            ps.setString(5, article.getArtCon());

            // 送出SQL語法
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.closeConnection(con, ps);
        }
        Debug.log("ArtcleDAOInsert done");
    }

    // 只更改文章內容、文章上下架狀態、文章標題與文章內容
    @Override
    public void update(int id, ArticleVO article) {
        Debug.log("ArtcleDAOUpdate方法");

        // 創建連線及放SQL用的物件
        Connection con = null;
        PreparedStatement ps = null;

        try{
            con = JDBCUtil.getConnection(); // 建立連線
            ps = con.prepareStatement(UPDATE);

            // 設定SQL裡的所有「?」
            ps.setByte(1, article.getArtCat());
            ps.setByte(2, article.getArtSta());
            ps.setString(3, article.getArtTitle());
            ps.setString(4, article.getArtCon());
            ps.setInt(5, id);

            // 送SQL進資料庫查詢
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtil.closeConnection(con, ps);
        }
        Debug.log("ArtcleDAOUpdate done");
    }

    // 更新文章除PK外全部欄位
    @Override
    public void update(ArticleVO article) {
        Debug.log("ArtcleDAOUpdate方法");

        // 創建連線及放SQL用的物件
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = JDBCUtil.getConnection();
            ps = con.prepareStatement(UPDATE_ALL); //SQL語法填入prepareStatement

            // 設定SQL裡的所有「?」
            ps.setByte(1, article.getArtCat());
            ps.setByte(2, article.getArtSta());
            ps.setInt(3, article.getArtHol());
            ps.setInt(4,article.getArtLike());
            ps.setString(5,article.getArtTitle());
            ps.setString(6,article.getArtCon());
            ps.setDate(7,new java.sql.Date(article.getArtCreTime().getTime()));

            //送出SQL指令
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.closeConnection(con, ps);
        }

        Debug.log("ArtcleDAOUpdate done");
    }

    @Override
    public Boolean delete(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        if (getById(id) == null) {
            Debug.errorLog("查無此文章" + " (文章編號: " + id + ")");
            return false;
        }

        try {
            con = JDBCUtil.getConnection();
            ps = con.prepareStatement(DELETE);
            ps.setInt(1, id);
            ps.executeUpdate(); //送出指令
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.closeConnection(con, ps);  //關資源
        }
        return true;
    }

    @Override
    public void delete(ArticleVO article) {
    }

    // 回傳全部文章
    @Override
    public List<ArticleVO> getAll() {
        Debug.log("ArtcleDAOgetAll方法");

        // 存最後要回傳的的資料列表，裡面的資料都是VO物件
        List<ArticleVO> articleList = new ArrayList<ArticleVO>();
        ArticleVO artv = null; // 用來塞資料進列表的VO

        Connection con = null;          // 資料庫連線
        PreparedStatement ps = null;    // 用來塞寫好的sql指令，如果指令內有?，也是藉由這個類別填入
        ResultSet rs = null;            // 查詢後的結果物件，可以用next查下一筆
        try {
            con = JDBCUtil.getConnection();     // 建立連線，連線資料跟方法都放在JDBCUtil裡面
            ps = con.prepareStatement(GET_ALL); // 把sql指令塞給PreparedStatement
            rs = ps.executeQuery();             // 送出指令，回傳查詢結果

            // 從回傳查詢結果裡，把對應的數值塞進PointChangesVO物件，成為一筆物件型態的資料
            while (rs.next()) {
                artv = new ArticleVO();
                artv.setArtId(rs.getInt("ARTID"));
                artv.setArtCat(rs.getByte("ARTCAT"));
                artv.setArtCon(rs.getString("ARTCON"));
                artv.setArtCreTime(rs.getDate("ARTCRETIME"));
                artv.setArtHol(rs.getInt("ARTHOL"));
                artv.setArtLike(rs.getInt("ARTLIKE"));
                artv.setArtSta(rs.getByte("ARTSTA"));
                artv.setArtTitle(rs.getString("ARTTITLE"));

                articleList.add(artv); // 把這筆物件型態的資料存入之後要回傳的List
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.closeConnection(con, ps, rs);  //關閉資源的方法也直接寫在JDBCUtil裡
        }
        Debug.log("找到" + articleList.size() + "筆資料");
        return articleList; // 回傳結果List
    }

    // 回傳全部文章DTO
    @Override
    public List<ArticleDTO> getAllDTO() {
        Debug.log("ArtcleDAOgetAllDTO方法");

        // 存最後要回傳的的資料列表，裡面的資料都是DTO物件
        List<ArticleDTO> articleList = new ArrayList<ArticleDTO>();
        ArticleDTO artd = null; // 用來塞資料進列表的DTO

        Connection con = null;          // 資料庫連線
        PreparedStatement ps = null;    // 用來塞寫好的sql指令，如果指令內有?，也是藉由這個類別填入
        ResultSet rs = null;            // 查詢後的結果物件，可以用next查下一筆
        try {
            con = JDBCUtil.getConnection();     // 建立連線，連線資料跟方法都放在JDBCUtil裡面
            ps = con.prepareStatement(GET_ALLDTO); // 把sql指令塞給PreparedStatement
            rs = ps.executeQuery();             // 送出指令，回傳查詢結果

            // 從回傳查詢結果裡，把對應的數值塞進PointChangesVO物件，成為一筆物件型態的資料
            while (rs.next()) {
                artd = new ArticleDTO();
                artd.setArtId(rs.getInt("ARTID"));
                artd.setArtCat(rs.getByte("ARTCAT"));
                artd.setArtCon(rs.getString("ARTCON"));
                artd.setArtCreTime(rs.getDate("ARTCRETIME"));
                artd.setMamName(rs.getString("MAMNAME"));
                artd.setArtLike(rs.getInt("ARTLIKE"));
                artd.setArtSta(rs.getByte("ARTSTA"));
                artd.setArtTitle(rs.getString("ARTTITLE"));

                articleList.add(artd); // 把這筆物件型態的資料存入之後要回傳的List
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.closeConnection(con, ps, rs);  //關閉資源的方法也直接寫在JDBCUtil裡
        }
        Debug.log("找到" + articleList.size() + "筆資料");
        return articleList; // 回傳結果List
    }

    // 用文章編號搜尋文章
    @Override
    public ArticleVO getById(int id) {
        Debug.log("ArtcleDAOgetById方法");

        ArticleVO artv = null;  //要拿來回傳的VO
        // 連線、指令及查詢結果的資源
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = JDBCUtil.getConnection();             //建立連線
            ps = con.prepareStatement(GET_ONE_BYARTID); //塞SQL指令
            ps.setInt(1, id);             //填SQL指令裡的?
            rs = ps.executeQuery();                     //送出指令

            // 從回傳查詢結果裡，把對應的數值塞進PointChangesVO物件，成為一筆物件型態的資料
            while (rs.next()) {
                artv = new ArticleVO();
                artv.setArtId(rs.getInt("ARTID"));
                artv.setArtCat(rs.getByte("ARTCAT"));
                artv.setArtCreTime(rs.getDate("ARTCRETIME"));
                artv.setArtHol(rs.getInt("ARTHOL"));
                artv.setArtLike(rs.getInt("ARTLIKE"));
                artv.setArtSta(rs.getByte("ARTSTA"));
                artv.setArtTitle(rs.getString("ARTTITLE"));
                artv.setArtCon(rs.getString("ARTCON"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.closeConnection(con, ps, rs);
        }

        return artv;
    }

    // DTO方法(使用JOIN的SQL語法
    @Override
    public ArticleDTO getDTOById(int id){
        Debug.log("ArtcleDAO_getDTOById方法");

        ArticleDTO artd = null;  //要拿來回傳的VO
        // 連線、指令及查詢結果的資源
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = JDBCUtil.getConnection();
            ps = con.prepareStatement(GET_DTO_BYARTID);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                artd = new ArticleDTO();
                artd.setArtId(rs.getInt("ARTID"));
                artd.setArtCat(rs.getByte("ARTCAT"));
                artd.setArtSta(rs.getByte("ARTSTA"));
                artd.setMamName(rs.getString("MAMNAME"));
                artd.setArtLike(rs.getInt("ARTLIKE"));
                artd.setArtTitle(rs.getString("ARTTITLE"));
                artd.setArtCon(rs.getString("ARTCON"));
                // TODO: 匯入文章留言
                artd.setArtCreTime(rs.getDate("ARTCRETIME"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.closeConnection(con, ps, rs);
        }

        return artd;
    }

    //測試用方法
    public static void main(String[] args) {
        ArticleJDBCDAO dao = new ArticleJDBCDAO();

//        // 新增
//        ArticleVO article = new ArticleVO((byte) 1, (byte) 1,3,"萬華","有夠讚");
//        dao.insert(article);

        // 更新
//        ArticleVO article = new ArticleVO(10,(byte)1,(byte)1,);

//        //全部欄位更新
//        ArticleVO article = new ArticleVO();

//        // 刪除
//        dao.delete(16);

        // 查單個
//        System.out.println(dao.getById(60));
//        Debug.log(dao.getById(60));

//        // 查詢全部
//        for (ArticleVO art : dao.getAll()) {
//            System.out.println(art);
//        }
    }
}
