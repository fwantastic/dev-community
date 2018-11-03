package com.community.dev.persistence;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "FILE_UPLOAD")
public class FileUpload implements Serializable {

	private static final long serialVersionUID = 4730818463267288624L;

	@Id
	@GeneratedValue
	@Column(name = "FILE_UPLOAD_ID")
	private Long fileUploadId;

	@Column(name = "ORIG_FILE_NAME")
	private String userNickname;

	@Column(name = "SAVE_FILE_NAME")
	private String password;

	@Column(name = "FILE_PATH")
	private String userEmail;

	@Column(name = "CONTENT_TYPE")
	private String emailSubscription;

	@Column(name = "FILE_SIZE")
	private Integer activityPoint;

	@Column(name = "IS_ACTIVE")
	@Type(type = "yes_no")
	private Boolean isActive;

	@Column(name = "CREATE_DTM")
	private LocalDateTime createDatetime;

	@Column(name = "UPDATE_DTM")
	private LocalDateTime updateDatetime;

	public Long getFileUploadId() {
		return fileUploadId;
	}

	public void setFileUploadId(Long fileUploadId) {
		this.fileUploadId = fileUploadId;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getEmailSubscription() {
		return emailSubscription;
	}

	public void setEmailSubscription(String emailSubscription) {
		this.emailSubscription = emailSubscription;
	}

	public Integer getActivityPoint() {
		return activityPoint;
	}

	public void setActivityPoint(Integer activityPoint) {
		this.activityPoint = activityPoint;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public LocalDateTime getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(LocalDateTime createDatetime) {
		this.createDatetime = createDatetime;
	}

	public LocalDateTime getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(LocalDateTime updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

}
