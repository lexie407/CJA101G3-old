package com.toiukha.forum.article.model;

import com.toiukha.forum.article.dto.ArticleDTO;
import com.toiukha.forum.article.entity.ArticleVO;

import java.util.List;

public interface IArticleService {
    List<ArticleVO> getAll();
    List<ArticleDTO> getAllDTO();
    ArticleVO getVOById(int id);
    ArticleDTO getDTOById(int id);

}
