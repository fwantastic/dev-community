package com.community.dev.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ARTICLE_TAG")
public class ArticleTag implements Serializable {

	private static final long serialVersionUID = 2764889021208185012L;

	@Id
	@Column(name = "ARTICLE_ID")
	private Long articleId;

	@Id
	@Column(name = "TAG_ID")
	private Long tagId;

}
