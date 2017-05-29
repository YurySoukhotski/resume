package com.senla.hotel.service.api;

import java.util.List;

import com.senla.hotel.model.Response;
import com.senla.hotel.model.Room;
import com.senla.hotel.model.RoomGalery;

public interface IManagerRoom {

	public List<Room> showSortedRoom(String type, Boolean isFree);

	public Response addNewRoom(Room room);

	public Response updateRoom(Room newR);

	public List<RoomGalery> getPhotos(Integer numRoom);
	
	public boolean addPhotoToRoom(RoomGalery  photo);


}
