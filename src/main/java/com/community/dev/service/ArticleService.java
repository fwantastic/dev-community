package com.community.dev.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.community.dev.persistence.Article;

public interface ArticleService {

	Article save(Article article);

	// List<Article> findAll(Pageable pageable);

	Page<Article> findAll(Pageable pageable);

	Article findByArticleId(Long articleId);

}