package com.senla.hotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "document_type")
public class Document implements Identified<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;

	@Column
	private String name;

	public void setId(Integer newId) {
		id = newId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		return sb.toString();

	}

	
	public Integer getId() {
		return id;
	}

}
