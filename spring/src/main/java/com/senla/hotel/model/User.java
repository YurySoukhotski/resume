package com.senla.hotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Identified<Integer> {

	private static final String ROLES ="roles";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;

	@Column
	private String name;
	
	@Column
	private String personalInfo;

	@Column
	private String pass;

	
	@Column(name = ROLES)
	@Enumerated(EnumType.STRING)
	private Roles roles;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	public Roles getRoles() {
		return roles;
	}


	public void setRoles(Roles roles) {
		this.roles = roles;
	}


	public String getPersonalInfo() {
		return personalInfo;
	}


	public void setPersonalInfo(String personalInfo) {
		this.personalInfo = personalInfo;
	}
	
	


}
