package com.community.dev.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TAG")
public class Tag implements Serializable {

	private static final long serialVersionUID = 7694550759510236637L;

	@Id
	@GeneratedValue
	@Column(name = "TAG_ID")
	private Long tagId;

	@Column(name = "TAG_NAME")
	private String tagName;

	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

}
