package com.community.dev.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "USER")
public class User {

	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Long userId;

	@Column(name = "USER_NICKNAME")
	private String userNickname;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "USER_EMAIL")
	private String userEmail;

	@Column(name = "EMAIL_SUBSCRIPTION")
	@Type(type = "yes_no")
	private Boolean emailSubscription;

	@Column(name = "ACTIVITY_POINT")
	private Long activityPoint;

	@Column(name = "IS_ACTIVE")
	@Type(type = "yes_no")
	private Boolean isActive;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Boolean getEmailSubscription() {
		return emailSubscription;
	}

	public void setEmailSubscription(Boolean emailSubscription) {
		this.emailSubscription = emailSubscription;
	}

	public Long getActivityPoint() {
		return activityPoint;
	}

	public void setActivityPoint(Long activityPoint) {
		this.activityPoint = activityPoint;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userNickname=" + userNickname + ", password=" + password + ", userEmail="
				+ userEmail + ", emailSubscription=" + emailSubscription + ", activityPoint=" + activityPoint
				+ ", isActive=" + isActive + "]";
	}

}
