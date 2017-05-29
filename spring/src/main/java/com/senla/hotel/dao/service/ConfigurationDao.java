package com.senla.hotel.dao.service;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.senla.hotel.dao.api.ICfgDao;
import com.senla.hotel.model.Configuration;
import com.senla.hotel.util.Constant;

@Repository
public class ConfigurationDao extends AbstractDao<Configuration, Integer> implements ICfgDao {

	public ConfigurationDao() {
		super(Configuration.class);
	}

	public Configuration getCfg() {

		Criteria criteria = createCriteria().addOrder(Order.desc(Constant.DATECONFIG)).setMaxResults(1);
		return (Configuration) criteria.uniqueResult();

	}
}
