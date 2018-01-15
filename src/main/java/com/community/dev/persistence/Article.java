package com.community.dev.persistence;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.community.dev.util.DateFormatUtility;

@Entity
@Table(name = "ARTICLE")
public class Article implements Serializable {

	private static final long serialVersionUID = 600730807324799624L;

	@Id
	@GeneratedValue
	@Column(name = "ARTICLE_ID")
	private Long articleId;

	// @ManyToOne
	// @JoinColumn(name = "CATEGORY_NAME")
	// private Category category;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "ARTICLE_TAG", joinColumns = {
			@JoinColumn(name = "ARTICLE_ID", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "TAG_ID", nullable = false, updatable = false) })
	private Set<Tag> tags = new HashSet<>();

	@Transient
	private String[] tagsArray;

	@Transient
	private List<Long> tagIdList;

	@ManyToOne
	@JoinColumn(name = "AUTHOR_USER_ID", referencedColumnName = "USER_ID")
	private User author;

	// @ManyToOne
	// @JoinColumn(name = "LAST_UPDATE_USER_ID", referencedColumnName =
	// "USER_ID")
	// private User lastEditedBy;

	@Column(name = "CREATE_DTM")
	private LocalDateTime createDatetime;

	@Column(name = "UPDATE_DTM")
	private LocalDateTime updateDatetime;

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

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public String[] getTagsArray() {
		return tagsArray;
	}

	public void setTagsArray(String[] tagsArray) {
		this.tagsArray = tagsArray;

		tagIdList = Stream.of(tagsArray).map(Long::parseLong).collect(Collectors.toList());

		// for (String tagId : tagsArray) {
		// Tag tag = new Tag();
		// tag.setTagId(Long.valueOf(tagId));
		// tags.add(tag);
		// }
	}

	public List<Long> getTagIdList() {
		return tagIdList;
	}

	public void setTagIdList(List<Long> tagIdList) {
		this.tagIdList = tagIdList;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getCreateDatetimeString() {
		return createDatetime.format(DateFormatUtility.DATE_TIME_FORMAT);
	}

	public LocalDateTime getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(LocalDateTime createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getUpdateDatetimeString() {
		return updateDatetime.format(DateFormatUtility.DATE_TIME_FORMAT);
	}

	public LocalDateTime getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(LocalDateTime updateDatetime) {
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
		return "Article [articleId=" + articleId + ", tags=" + tags + ", tagsArray=" + Arrays.toString(tagsArray)
				+ ", author=" + author + ", createDatetime=" + createDatetime + ", updateDatetime=" + updateDatetime
				+ ", title=" + title + ", viewCount=" + viewCount + ", likeCount=" + likeCount + ", contents="
				+ contents + ", isActive=" + isActive + "]";
	}

}
