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
@Table(name = "analitics")
public class Analitics  implements Identified<Integer> {
	
	private static final String DATE = "date";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name = DATE)
	private Date date;
	
	
	@Column
	private Integer summPeriod;
	
	@Column
	private Integer countGuest;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getSummPeriod() {
		return summPeriod;
	}

	public void setSummPeriod(Integer summPeriod) {
		this.summPeriod = summPeriod;
	}

	public Integer getCountGuest() {
		return countGuest;
	}

	public void setCountGuest(Integer countGuest) {
		this.countGuest = countGuest;
	}
	
	



}
