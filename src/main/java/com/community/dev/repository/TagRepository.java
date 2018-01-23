package com.community.dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.community.dev.persistence.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

	// @Query("SELECT t FROM Tag t WHERE tagId IN (:tagIds)")
	// List<Tag> findByTagIds(@Param("tagIds") String tagIds);

	List<Tag> findByTagIdIn(List<Long> tagIdList);

	List<Tag> findAllByOrderByTagName();

}
