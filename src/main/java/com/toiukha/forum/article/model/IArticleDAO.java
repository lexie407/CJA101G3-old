package com.toiukha.forum.article.model;

import com.toiukha.forum.article.entity.ArticleVO;

import java.util.List;

public interface IArticleDAO {
    public void add(ArticleVO article);
    public void update(ArticleVO article);
    public void delete(int id);
    public void delete(ArticleVO article);
    List<ArticleVO> getAll();
    ArticleVO getById(int id);
}
