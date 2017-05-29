package com.senla.hotel.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "guest")
public class Guest implements Identified<Integer> {

	private static final String COUNTRYID = "country_id";
	private static final String DOCUMENTID = "document_id";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;

	@Column
	private String name;

	@Column
	private String surname;

	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = COUNTRYID)
	private Country country;

	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = DOCUMENTID)
	private Document document;

	@Column
	private String docNumber;

	@Column
	private String docFileName;

	@Column
	private String email;

	@Column
	private String phone;

	@Column
	private Integer amount;

	public void setId(Integer newId) {
		id = newId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public String getDocNumber() {
		return docNumber;
	}

	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}

	public String getDocFileName() {
		return docFileName;
	}

	public void setDocFileName(String docFileName) {
		this.docFileName = docFileName;
	}

	public Integer getId() {

		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}
