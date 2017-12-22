package com.community.dev.persistence;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//@Entity
//@Table(name = "ARTICLE")
public class Article {

	@Id
	@Column(name = "ARTICLE_ID")
	private Long articleId;

	@ManyToOne
	@JoinColumn(name = "CATEGORY_NAME")
	private Category category;

	// @Column
	// private Set<Tag> tags = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "AUTHOR_USER_ID", referencedColumnName = "USER_ID")
	private User author;

	@ManyToOne
	@JoinColumn(name = "LAST_UPDATE_USER_ID", referencedColumnName = "USER_ID")
	private User lastEditedBy;

	@Column(name = "ARTICLE_CREATE_DTM")
	private Date createDatetime;

	@Column(name = "ARTICLE_UPDATE_DTM")
	private Date updateDatetime;

	@Column(name = "ARTICLE_TITLE")
	private String title;

	@Column(name = "ARTICLE_VIEW_CNT")
	private Long viewCount;

	@Column(name = "ARTICLE_LIKE_CNT")
	private Long likeCount;

	@Column(name = "ARTICLE_CONTENTS")
	private String contents;

	@Column(name = "IS_ACTIVE")
	private Boolean isActive;

}
