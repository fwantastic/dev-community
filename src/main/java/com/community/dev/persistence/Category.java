package com.community.dev.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORY")
public class Category {

	@Id
	@Column(name = "CATEGORY_NAME")
	private String categoryName;

	@Column(name = "CATEGORY_LVL")
	private Integer categoryLevel;

}
