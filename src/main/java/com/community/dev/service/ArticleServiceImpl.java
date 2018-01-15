package com.community.dev.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.community.dev.persistence.Article;
import com.community.dev.persistence.Tag;
import com.community.dev.repository.ArticleRepository;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private TagService tagService;

	@Override
	public Article save(Article article) {
		List<Tag> tags = tagService.findByTagIdIn(article.getTagIdList());
		article.setTags(new HashSet<Tag>(tags));

		LocalDateTime now = LocalDateTime.now();

		if (article.getCreateDatetime() == null) {
			article.setCreateDatetime(now);
		}

		article.setUpdateDatetime(now);

		articleRepository.save(article);
		return null;
	}

	// @Override
	// public List<Article> findAll(Pageable pageable) {
	// Page<Article> page = articleRepository.findAll(pageable);
	// return page.getContent();
	// }

	@Override
	public Page<Article> findAll(Pageable pageable) {
		return articleRepository.findAll(pageable);
	}

	@Override
	public Article findByArticleId(Long articleId) {
		return articleRepository.findOne(articleId);
	}

}
