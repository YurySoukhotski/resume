package com.senla.hotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "roomgalery")
public class RoomGalery {

	private static final String ROOMID = "room_id";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	@Expose
	private Integer id;

	@Column
	@Expose
	private String file_name;

	@ManyToOne
	@JoinColumn(name = ROOMID)
	private Room room;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	

}
