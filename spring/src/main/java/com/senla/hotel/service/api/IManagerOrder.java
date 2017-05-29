package com.senla.hotel.service.api;

import java.util.List;

import com.senla.hotel.model.CustomerService;
import com.senla.hotel.model.Orders;
import com.senla.hotel.model.Response;
import com.senla.hotel.model.Room;
import com.senla.hotel.model.SimpleOrder;
import com.senla.hotel.model.SimpleRoomService;



public interface IManagerOrder {

	public Response closeOrder(Integer i);

	public List<Orders> showHistoryNumber(Integer nNumber);

	public List<Orders> getOrders();
	
	public List<Room> showFreeNumbersDate(String dateF);

	/**
	 * 
	 * work with service in order
	 * 
	 */
	
	public List<CustomerService> showInfoAboutServices(Integer numOrd);
	
	public Response addServiceToOrder(SimpleRoomService simplRs);

	public Response createOrder(SimpleOrder ord);

	public List<Orders> showHistoryGuest(Integer guestId);

	public List<Orders> getSheduller();

	public Response getCountGuest(String date);
	
		

}