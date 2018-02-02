package com.community.dev.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.community.dev.persistence.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

	Page<Article> findDistinctByTags_TagIdIn(List<Long> tagIdList, Pageable pageable);

}
