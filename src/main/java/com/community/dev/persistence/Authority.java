package com.community.dev.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AUTHORITY")
public class Authority implements Serializable {

	private static final long serialVersionUID = -1187709955235265737L;

	@Id
	@Column(name = "USER_ID")
	private Long userId;

	@Column(name = "AUTHORITY_NAME")
	private String authorityName;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

}
