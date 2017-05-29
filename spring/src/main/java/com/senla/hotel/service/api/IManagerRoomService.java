package com.senla.hotel.service.api;

import java.util.List;

import com.senla.hotel.model.Response;
import com.senla.hotel.model.RoomService;

public interface IManagerRoomService {

	public Response addNewService(RoomService newS) ;

	public Response changeService(RoomService srv);

	public List<RoomService> showSortedService();

	

}
