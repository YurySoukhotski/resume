package com.senla.hotel.dao.api;

import com.senla.hotel.model.Guest;

public interface IGuestDao {

	public Guest getGuestByNameSurname(String name, String surname) throws Exception;
}
