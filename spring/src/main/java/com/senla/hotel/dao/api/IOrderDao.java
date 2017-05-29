package com.senla.hotel.dao.api;

import java.util.Date;
import java.util.List;

import com.senla.hotel.model.CustomerService;
import com.senla.hotel.model.Orders;

public interface IOrderDao {

	public List<CustomerService> getInfobyService(Integer numOrd) throws Exception;

	public List<Orders> getLastOrders(Integer nNumber);

	public List<Orders> getFreeNumberOnDate( Date date);

	public List<Orders> getHistGuest(Integer guestId);
	
	public List<Orders> getListOrder();
}
