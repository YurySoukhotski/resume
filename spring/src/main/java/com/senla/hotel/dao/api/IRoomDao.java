package com.senla.hotel.dao.api;

import java.util.List;

import com.senla.hotel.model.RoomGalery;

public interface IRoomDao {

	public List<RoomGalery> getPhotos(Integer numRoom);
	
}
