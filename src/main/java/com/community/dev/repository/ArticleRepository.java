package com.community.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.community.dev.persistence.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
