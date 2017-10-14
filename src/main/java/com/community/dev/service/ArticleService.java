package com.community.dev.service;

import java.util.List;

import com.community.dev.persistence.Article;

public interface ArticleService {

	Article create(Article article);

	Article save(Article article);

	List<Article> findAll();

	Article findByArticleId(Long articleId);

}