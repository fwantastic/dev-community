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
	public Tag create(Tag tag) {
		tagRepository.save(tag);
		return null;
	}

	@Override
	public Tag save(Tag tag) {
		tagRepository.save(tag);
		return null;
	}

	@Override
	public List<Tag> findAllByOrderByTagName() {
		return tagRepository.findAllByOrderByTagName();
	}

	@Override
	public Page<Tag> findAllByOrderByTagName(Pageable pageable) {
		return tagRepository.findAllByOrderByTagName(pageable);
	}

	@Override
	public Tag findByTagId(Long tagId) {
		return tagRepository.findOne(tagId);
	}

	@Override
	public List<Tag> findByTagIdIn(List<Long> tagIdList) {

		if (tagIdList == null || tagIdList.size() <= 0) {
			return new ArrayList<>();
		}

		List<Tag> tags = tagRepository.findByTagIdIn(tagIdList);

		if (tags == null) {
			return new ArrayList<>();
		}

		return tags;
	}

}
