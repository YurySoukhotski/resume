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
@Table(name = "configuration")
public class Configuration  implements Identified<Integer> {
	private static final String DATE = "date";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;

	@Column
	private String hotelName;
	
	@Column
	private String hotelEmail;

	@Column
	private String hotelPhones;
	
	@Column
	private String hotelFax;

	@Column
	private String adress;
	
	@Column
	private Integer discountLevelFive;
	
	@Column
	private Integer discountLevelTen;
	
	@Temporal(TemporalType.DATE)
	@Column(name = DATE)
	private Date dateConfig;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getHotelName() {
		return hotelName;
	}


	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}


	public String getHotelEmail() {
		return hotelEmail;
	}


	public void setHotelEmail(String hotelEmail) {
		this.hotelEmail = hotelEmail;
	}


	public String getHotelPhones() {
		return hotelPhones;
	}


	public void setHotelPhones(String hotelPhones) {
		this.hotelPhones = hotelPhones;
	}


	public String getHotelFax() {
		return hotelFax;
	}


	public void setHotelFax(String hotelFax) {
		this.hotelFax = hotelFax;
	}


	public String getAdress() {
		return adress;
	}


	public void setAdress(String adress) {
		this.adress = adress;
	}


	public Date getDateConfig() {
		return dateConfig;
	}


	public void setDateConfig(Date dateConfig) {
		this.dateConfig = dateConfig;
	}


	public Integer getDiscountLevelFive() {
		return discountLevelFive;
	}


	public void setDiscountLevelFive(Integer discountLevelFive) {
		this.discountLevelFive = discountLevelFive;
	}


	public Integer getDiscountLevelTen() {
		return discountLevelTen;
	}


	public void setDiscountLevelTen(Integer discountLevelTen) {
		this.discountLevelTen = discountLevelTen;
	}

	
}
