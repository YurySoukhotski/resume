package com.senla.hotel.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senla.hotel.dao.api.IPaymentDao;
import com.senla.hotel.dao.service.AbstractDao;
import com.senla.hotel.dao.service.PaymentDao;
import com.senla.hotel.model.Payment;
import com.senla.hotel.model.Response;
import com.senla.hotel.service.api.IManagerPayment;
import com.senla.hotel.util.Calend;
import com.senla.hotel.util.Constant;

@Service
@SuppressWarnings("unchecked")
public class ManagerPayment implements IManagerPayment {
	private static Logger log = LogManager.getLogger(ManagerPayment.class);

	@Autowired
	private IPaymentDao payDao;

	
	@Transactional
	public Response addPayment(Payment payment) {
		Response rs = new Response();
		rs.setName(Constant.SAVEENT);
		try {
			((AbstractDao<Payment, Integer>) payDao).save(payment);
			rs.setBody(Constant.SAVED);
		} catch (Exception e) {
			log.error(e.getMessage());
			rs.setBody(Constant.ERROR);
		}
		
		return rs;

	}

	@Transactional
	public List<Payment> getPayments() {
		try {
			return ((AbstractDao<Payment, Integer>) payDao).getAll();
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	@Transactional
	public List<Payment> getPaymentsById(Integer numOrd) {
		try {
			return payDao.getPaymentByOrder(numOrd);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		} 

	}

	@Transactional
	public Response getPaymentsByDate(String date) {
		Response rs = new Response();
		rs.setName(Constant.GETPAYMENT);
		try {
			List<Payment> list = ((PaymentDao) payDao).getPaymentByDate(Calend.getDateText(date));
			Integer count=0;
			for (Payment p: list){
				count=count+p.getSumm();
			}
			rs.setBody(count.toString());
		} catch (Exception e) {
			log.error(e.getMessage());
			rs.setBody(Constant.ERROR);
			
		} 
		return rs;
	}

}
