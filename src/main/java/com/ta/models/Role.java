package com.ta.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ta.enums.RoleType;

@Document(collection = "Roles")
public class Role {

	@Id
	private String id;
	private RoleType name;
	
	public Role() {}
	
	public Role(RoleType name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public RoleType getName() {
		return name;
	}

	public void setName(RoleType name) {
		this.name = name;
	}
}
