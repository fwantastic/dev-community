package com.community.dev.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table
public class Role {

	@Id
	@Column
	private Long roleId;

	@Column
	private String roleName;

}
