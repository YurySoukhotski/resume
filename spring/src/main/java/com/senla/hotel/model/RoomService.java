
package com.senla.hotel.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "service")
public class RoomService implements Serializable, Identified<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2081809706890098254L;
	private static final String NAME ="name";
	private static final String PRICE = "price";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;

	@Column(name = NAME)
	private String sName;

	@Column
	private String description;
	
	@Column(name = PRICE)
	private Integer sPrice;

	public Integer getId() {
		return id;
	}

	public void setId(Integer serviceId) {
		this.id = serviceId;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public Integer getsPrice() {
		return sPrice;
	}

	public void setsPrice(Integer sPrice) {
		this.sPrice = sPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

}
