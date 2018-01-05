package com.community.dev.persistence;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.community.dev.util.DateFormatUtility;

@Entity
@Table(name = "ARTICLE")
public class Article {

	@Id
	@GeneratedValue
	@Column(name = "ARTICLE_ID")
	private Long articleId;

	// @ManyToOne
	// @JoinColumn(name = "CATEGORY_NAME")
	// private Category category;

	// @Column
	// private Set<Tag> tags = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "AUTHOR_USER_ID", referencedColumnName = "USER_ID")
	private User author;

	// @ManyToOne
	// @JoinColumn(name = "LAST_UPDATE_USER_ID", referencedColumnName =
	// "USER_ID")
	// private User lastEditedBy;

	@Column(name = "CREATE_DTM")
	private Date createDatetime;

	@Column(name = "UPDATE_DTM")
	private Date updateDatetime;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "VIEW_COUNT")
	private Long viewCount;

	@Column(name = "LIKE_COUNT")
	private Long likeCount;

	@Column(name = "CONTENTS")
	private String contents;

	@Column(name = "IS_ACTIVE")
	private Boolean isActive;

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getCreateDatetimeString() {
		return DateFormatUtility.parseSimpleDate(createDatetime);
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public Date getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getViewCount() {
		return viewCount;
	}

	public void setViewCount(Long viewCount) {
		this.viewCount = viewCount;
	}

	public Long getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Long likeCount) {
		this.likeCount = likeCount;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String convertContents() {
		return contents.replaceAll("(\\r|\\n|\\r\\n)+", "\\\\n");
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", createDatetime=" + createDatetime + ", updateDatetime="
				+ updateDatetime + ", title=" + title + ", viewCount=" + viewCount + ", likeCount=" + likeCount
				+ ", contents=" + contents + ", isActive=" + isActive + "]";
	}

}
