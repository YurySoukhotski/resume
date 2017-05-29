package com.senla.hotel.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "news")
public class News  implements Identified<Integer> {

	private static final String DATE = "date";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;

	@Column
	private String simple_name;

	@Temporal(TemporalType.DATE)
	@Column(name = DATE)
	private Date date;

	
	@Column
	private String full_text;
	
	
	@Column
	private Integer user_id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSimple_name() {
		return simple_name;
	}

	public void setSimple_name(String simple_name) {
		this.simple_name = simple_name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFull_text() {
		return full_text;
	}

	public void setFull_text(String full_text) {
		this.full_text = full_text;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	
}
