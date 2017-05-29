package com.senla.hotel.dao.service;

import java.util.Date;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.senla.hotel.dao.api.IAnaliticsDao;
import com.senla.hotel.model.Analitics;

@Repository
public class AnaliticsDao extends AbstractDao<Analitics, Integer> implements IAnaliticsDao {

	private static final String DATE = "date";


	public AnaliticsDao() {
		super(Analitics.class);
	}

	public Analitics getAnaliticsOnDate(Date date) {
		Criteria criteria = createCriteria()
				.add(Restrictions.eq(DATE, date));
		return (Analitics) criteria.uniqueResult();

	}
}