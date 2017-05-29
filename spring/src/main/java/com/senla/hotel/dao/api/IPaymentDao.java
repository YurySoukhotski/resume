package com.senla.hotel.dao.api;

import java.util.List;

import com.senla.hotel.model.Payment;

public interface IPaymentDao {

	public List<Payment> getPaymentByOrder(Integer numOrd) throws Exception;
	
}
