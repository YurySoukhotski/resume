package com.senla.hotel.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "rooms")
public class Room implements Cloneable, Serializable, Identified<Integer> {

	private static final long serialVersionUID = 5602033422720361745L;

	private static final String ROOM = "room";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	@Expose
	private Integer id;

	@Column
	@Expose
	private Integer number;

	@Column
	@Expose
	private String classRoom;

	@Column
	@Expose
	private boolean hasBed;

	@Column
	@Expose
	private boolean hasTV;

	@Column
	@Expose
	private boolean hasShower;

	@Column
	@Expose
	private boolean hasBathroom;

	@Column
	@Expose
	private boolean hasSafe;

	@Column
	@Expose
	private boolean hasFridge;

	@Column
	@Expose
	private boolean hasHairdryer;

	@Column
	@Expose
	private String otherOptions;

	@Column
	@Expose
	private Integer capacity;

	@Column
	@Expose
	private Integer price;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = ROOM)
	private List<RoomGalery> galery = new ArrayList<RoomGalery>();

	@Column
	@Expose
	@Enumerated(EnumType.STRING)
	private RoomStatus status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(String classRoom) {
		this.classRoom = classRoom;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public RoomStatus getStatus() {
		return status;
	}

	public void setStatus(RoomStatus status) {
		this.status = status;
	}

	public boolean isHasBed() {
		return hasBed;
	}

	public void setHasBed(boolean hasBed) {
		this.hasBed = hasBed;
	}

	public boolean isHasTV() {
		return hasTV;
	}

	public void setHasTV(boolean hasTV) {
		this.hasTV = hasTV;
	}

	public boolean isHasShower() {
		return hasShower;
	}

	public void setHasShower(boolean hasShower) {
		this.hasShower = hasShower;
	}

	public boolean isHasBathroom() {
		return hasBathroom;
	}

	public void setHasBathroom(boolean hasBathroom) {
		this.hasBathroom = hasBathroom;
	}

	public boolean isHasSafe() {
		return hasSafe;
	}

	public void setHasSafe(boolean hasSafe) {
		this.hasSafe = hasSafe;
	}

	public boolean isHasFridge() {
		return hasFridge;
	}

	public void setHasFridge(boolean hasFridge) {
		this.hasFridge = hasFridge;
	}

	public boolean isHasHairdryer() {
		return hasHairdryer;
	}

	public void setHasHairdryer(boolean hasHairdryer) {
		this.hasHairdryer = hasHairdryer;
	}

	public String getOtherOptions() {
		return otherOptions;
	}

	public void setOtherOptions(String otherOptions) {
		this.otherOptions = otherOptions;
	}

	public List<RoomGalery> getGalery() {
		return galery;
	}

	public void setGalery(List<RoomGalery> galery) {
		this.galery = galery;
	}

	public Room clone() throws CloneNotSupportedException {

		return (Room) super.clone();
	}

}
