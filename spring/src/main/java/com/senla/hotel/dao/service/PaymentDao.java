package com.senla.hotel.dao.service;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.senla.hotel.dao.api.IPaymentDao;
import com.senla.hotel.model.Payment;
import com.senla.hotel.util.Constant;
@Repository
public class PaymentDao extends AbstractDao<Payment, Integer> implements IPaymentDao{

	public PaymentDao() {
		super(Payment.class);
	}

	@SuppressWarnings("unchecked")
	public List<Payment> getPaymentByOrder(Integer numOrd) throws Exception {
		Criteria criteria = createCriteria().add(Restrictions.eq(Constant.ORDER, numOrd));
		return criteria.list();

	}

	@SuppressWarnings("unchecked")
	public List<Payment> getPaymentByDate(Date dateText) {
		Criteria criteria = createCriteria().add(Restrictions.eq(Constant.DATEPAYMENT, dateText));
		return criteria.list();
	}

	
}
