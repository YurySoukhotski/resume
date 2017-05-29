package com.senla.hotel.service.api;

import java.util.List;

import com.senla.hotel.model.Payment;
import com.senla.hotel.model.Response;

public interface IManagerPayment {

	/**
	 * 
	 *  work with payments
	 */
	public Response addPayment(Payment payment);

	public List<Payment> getPayments();

	public List<Payment> getPaymentsById(Integer orderId);

	public Response getPaymentsByDate(String date);
	

	
}
