package com.community.dev.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table
public class Activity {

	@Id
	@Column
	private Long activityId;

	@Column
	private String activityTypeCode;

	@Column
	private User author;

	@Column
	private Article article;

	@Column
	private Long point;

}
