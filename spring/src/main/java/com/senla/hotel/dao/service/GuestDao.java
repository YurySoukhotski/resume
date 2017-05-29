package com.senla.hotel.dao.service;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.senla.hotel.dao.api.IGuestDao;
import com.senla.hotel.model.Guest;
import com.senla.hotel.util.Constant;

@Repository
public class GuestDao extends AbstractDao<Guest, Integer> implements IGuestDao {

	public GuestDao() {
		super(Guest.class);
	}

	
	public Guest getGuestByNameSurname(String name, String surname) throws Exception {
		Criteria criteria = createCriteria();
		Criterion n = Restrictions.eq(Constant.NAME, name);
		Criterion s = Restrictions.eq(Constant.SURNAME, surname);
		LogicalExpression orExp = Restrictions.and(s, n);
		criteria.add( orExp );
		return (Guest) criteria.uniqueResult();
		

	}

}
