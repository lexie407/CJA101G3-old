package com.toiukha.forum.article.model;

import com.toiukha.forum.article.dto.ArticleDTO;
import com.toiukha.forum.article.entity.ArticleVO;

import java.util.List;

// 這個介面定義DAO該是什麼格式
public interface IArticleDAO {
    public void insert(ArticleVO article);
    public void update(ArticleVO article);
    public void update(int id, ArticleVO article); //一般情況應使用這個
    public Boolean delete(int id);
    public void delete(ArticleVO article);
    List<ArticleVO> getAll();
    List<ArticleDTO> getAllDTO();
    ArticleVO getById(int id);
    ArticleDTO getDTOById(int id);
}
