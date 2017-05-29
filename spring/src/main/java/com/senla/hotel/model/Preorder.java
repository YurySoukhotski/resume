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
@Table(name = "preorder")
public class Preorder implements Identified<Integer> {

	private static final String COUNTRYID = "country_id";
	private static final String IDROOM = "idRoom";
	private static final String DATEST = "dateSt";
	private static final String DATEND = "dateEnd";

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String nameGuest;

	@Column
	private String surnameGuest;

	@Column
	private String emailGuest;

	@Column
	private String phoneGuest;

	@Column(name = COUNTRYID)
	private Integer country;

	@Column
	private String additionalInfo;

	@Column(name = IDROOM)
	private Integer room;

	@Temporal(TemporalType.DATE)
	@Column(name = DATEST)
	private Date dateStart;

	@Temporal(TemporalType.DATE)
	@Column(name = DATEND)
	private Date dateEnd;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameGuest() {
		return nameGuest;
	}

	public void setNameGuest(String nameGuest) {
		this.nameGuest = nameGuest;
	}

	public String getEmailGuest() {
		return emailGuest;
	}

	public void setEmailGuest(String emailGuest) {
		this.emailGuest = emailGuest;
	}

	public String getPhoneGuest() {
		return phoneGuest;
	}

	public void setPhoneGuest(String phoneGuest) {
		this.phoneGuest = phoneGuest;
	}

	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public Integer getRoom() {
		return room;
	}

	public void setRoom(Integer room) {
		this.room = room;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getSurnameGuest() {
		return surnameGuest;
	}

	public void setSurnameGuest(String surnameGuest) {
		this.surnameGuest = surnameGuest;
	}

}
