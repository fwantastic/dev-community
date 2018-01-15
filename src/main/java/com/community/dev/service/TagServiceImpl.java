package com.community.dev.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.community.dev.persistence.Article;
import com.community.dev.persistence.Tag;
import com.community.dev.repository.TagRepository;

@Service
@Transactional
public class TagServiceImpl implements TagService {

	@Autowired
	private TagRepository tagRepository;

	@Override
	public Article create(Tag tag) {
		tagRepository.save(tag);
		return null;
	}

	@Override
	public Article save(Tag tag) {
		tagRepository.save(tag);
		return null;
	}

	@Override
	public Page<Tag> findAll(Pageable pageable) {
		return tagRepository.findAll(pageable);
	}

	@Override
	public Tag findByTagId(Long tagId) {
		return tagRepository.findOne(tagId);
	}

	@Override
	public List<Tag> findByTagIdIn(List<Long> tagIdList) {
		List<Tag> tags = tagRepository.findByTagIdIn(tagIdList);

		if (tags == null) {
			return new ArrayList<>();
		}

		return tags;
	}

}
