package com.community.dev.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.community.dev.persistence.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

	List<Tag> findByTagIdIn(List<Long> tagIdList);

	List<Tag> findAllByOrderByTagName();

	Page<Tag> findAllByOrderByTagName(Pageable pageable);

}
