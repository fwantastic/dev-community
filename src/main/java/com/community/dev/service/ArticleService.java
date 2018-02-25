package com.community.dev.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.community.dev.persistence.Article;

public interface ArticleService {

	Article save(Article article);

	Page<Article> findAll(Pageable pageable);

	Article findByArticleId(Long articleId);

	Page<Article> findByTags_TagIdIn(List<Long> tagIdList, Pageable pageable);

}