package com.community.dev.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.community.dev.persistence.Article;
import com.community.dev.persistence.Tag;

public interface TagService {

	Article create(Tag tag);

	Article save(Tag tag);

	Page<Tag> findAll(Pageable pageable);

	Tag findByTagId(Long tagId);

	List<Tag> findByTagIdIn(List<Long> tagIdList);

}