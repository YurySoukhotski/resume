package com.senla.hotel.dao.service;

import org.springframework.stereotype.Repository;

import com.senla.hotel.dao.api.ICountryDao;
import com.senla.hotel.model.Country;
@Repository
public class CountryDao extends AbstractDao<Country, Integer> implements ICountryDao{

	public CountryDao() {
		super(Country.class);
	}


}
