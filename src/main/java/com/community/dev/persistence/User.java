package com.community.dev.persistence;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "USER")
public class User implements Serializable {

	private static final long serialVersionUID = 2571940587770509341L;

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

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "ROLE_ID") })
	private Set<Role> roles = new HashSet<>();

	@Column(name = "IS_ACTIVE")
	@Type(type = "yes_no")
	private Boolean isActive;

	@Column(name = "CREATE_DTM")
	private LocalDateTime createDatetime;

	@Column(name = "UPDATE_DTM")
	private LocalDateTime updateDatetime;

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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
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

	public String getStatus() {
		if (isActive) {
			return "Active";
		}

		return "Inactive";
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userNickname=" + userNickname + ", password=" + password + ", userEmail="
				+ userEmail + ", emailSubscription=" + emailSubscription + ", activityPoint=" + activityPoint
				+ ", roles=" + roles + ", isActive=" + isActive + ", createDatetime=" + createDatetime
				+ ", updateDatetime=" + updateDatetime + "]";
	}

}
