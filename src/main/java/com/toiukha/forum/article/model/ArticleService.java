package com.toiukha.forum.article.model;

import com.toiukha.forum.article.dto.ArticleDTO;
import com.toiukha.forum.article.entity.ArticleVO;
import com.toiukha.forum.util.Debug;
import com.toiukha.members.model.*;

import java.util.List;

// 負責寫驗證、商業邏輯等等
public class ArticleService implements IArticleService {
    private IArticleDAO dao = new ArticleJDBCDAO();

    @Override
    public ArticleDTO getDTOById(int id) {
        ArticleDTO artd = dao.getDTOById(id);
        return artd;
    }

    public ArticleVO getVOById(int id) {
        return dao.getById(id);
    }

    @Override
    public List<ArticleVO> getAll() {
        return dao.getAll();
    }

    @Override
    public List<ArticleDTO> getAllDTO(){
        return dao.getAllDTO();
    }

    /* 使用MembersDAO得到ArticleDTO的會員名字

    private MembersDAO_interface mdao = new MembersDAO();   // 會員資料的dao
    // 這段會在 mdao.findByPrimaryKey(id); 時報錯，因為MembersDAO找不到ds
    // 針對此例外，將來應該要有更妥善的處理方式(目前是將會員姓名用文章的會員編號作為替代)
    public MembersVO getMember(int id) {
        MembersVO mvo = null;  //用來拿會員名稱資料
        // 例外處理
        try {
            mvo = mdao.findByPrimaryKey(id);
        } catch (RuntimeException e) {
            Debug.errorLog(e.getMessage());
            mvo = new MembersVO();
            mvo.setMemId(id);
            mvo.setMemName(new String(""+ id));
        }
        return mvo;
    }


    public ArticleDTO getDTOById(int id) {
        ArticleVO artv = getVOById(id);

        MembersVO mvo = null;   //用來拿會員名稱資料
        mvo = getMember(id);

        ArticleDTO artd = null; //準備要回傳的資料

        if (artv != null && mvo != null) {
            artd = new ArticleDTO(artv.getArtId(),artv.getArtCat(),artv.getArtSta()
                    ,mvo.getMemName(),artv.getArtLike(),artv.getArtTitle(),artv.getArtCon()
                    ,artv.getArtCreTime());
        }else {
            Debug.errorLog(artv == null ? "文章為空值" : "有文章資料", mvo == null ? "會員資料為空值" : "有會員資料");
        }
        return artd;
    }
    */


    public static void main(String[] args) {
        ArticleService as = new ArticleService();
        ArticleDTO dto = as.getDTOById(1);
        System.out.println(dto);
    }
}
