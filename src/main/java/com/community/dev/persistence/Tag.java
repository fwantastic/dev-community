package com.community.dev.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table
public class Tag {

	@Id
	@Column
	private String tagName;
	
}
