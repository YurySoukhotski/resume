package com.senla.hotel.model;

import java.util.Date;


public class SimpleOrder{

	private Integer idRoom;

	private Integer idGuest;

	private Date dateSt;

	private Date dateEnd;

	public Integer getIdRoom() {
		return idRoom;
	}

	public void setIdRoom(Integer idRoom) {
		this.idRoom = idRoom;
	}

	public Integer getIdGuest() {
		return idGuest;
	}

	public void setIdGuest(Integer idGuest) {
		this.idGuest = idGuest;
	}

	public Date getDateSt() {
		return dateSt;
	}

	public void setDateSt(Date dateSt) {
		this.dateSt = dateSt;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	
		
}
