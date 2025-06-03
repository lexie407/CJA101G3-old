package com.toiukha.forum.article.model;

import com.toiukha.forum.article.entity.ArticleVO;
import com.toiukha.forum.util.JDBCUtil;

import java.util.List;

public class ArticleJDBCDAO implements IArticleDAO {

    /* TODO: 新增修改刪除的定義
     *   最小MVP：新增、修改、瀏覽全部文章、瀏覽單一文章（附帶留言） */

    // 當類別載入時會執行這一個區塊，叫JVM載入放著驅動資訊的JDBCUtil
    static {
        try {
            Class.forName(JDBCUtil.DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(ArticleVO article) {

    }

    @Override
    public void update(ArticleVO article) {

    }

    @Override
    public void delete(int id) {
        ArticleVO article = getById(id);
    }

    @Override
    public void delete(ArticleVO article) {

    }

    @Override
    public List<ArticleVO> getAll() {

        return List.of();
    }

    @Override
    public ArticleVO getById(int id) {
        return null;
    }
}
