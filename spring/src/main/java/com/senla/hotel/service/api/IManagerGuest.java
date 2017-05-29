package com.senla.hotel.service.api;

import java.util.List;

import com.senla.hotel.model.Guest;
import com.senla.hotel.model.Response;

public interface IManagerGuest {

	public List<Guest> getListGuest();

	public Guest getGuestById(String nom);

	public Response addGuest(Guest guest);
	
	public Response updateGuest(Guest guest);
	
	public Response deleteGuest(Guest guest);
	

	
}
