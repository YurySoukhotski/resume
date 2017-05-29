package com.senla.hotel.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senla.hotel.dao.api.ICountryDao;
import com.senla.hotel.dao.service.AbstractDao;
import com.senla.hotel.model.Country;
import com.senla.hotel.model.Response;
import com.senla.hotel.service.api.IManagerCountry;
import com.senla.hotel.util.Constant;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class ManagerCountry implements IManagerCountry {
	private static Logger log = LogManager.getLogger(ManagerCountry.class);

	@Autowired
	private ICountryDao countryDao;

	public List<Country> getCountries() {
		try {
			return ((AbstractDao<Country, Integer>) countryDao).getAll(Constant.ID, false);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public Response changeCountry(Country country) {
		Response rs = new Response();
		rs.setName(Constant.UPDATECOUNTRY);
		try {
			((AbstractDao<Country, Integer>) countryDao).update(country);
			rs.setBody(Constant.UPDATED);
		} catch (Exception e) {
			log.error(e.getMessage());
			rs.setBody(Constant.ERROR);
		}
		return rs;

	}

	@Override
	public Response addCountry(Country cntr) {
		Response rs = new Response();
		rs.setName(Constant.ADDCOUNTRY);
		try {
			((AbstractDao<Country, Integer>) countryDao).save(cntr);
			rs.setBody(Constant.SAVED);
		} catch (Exception e) {
			log.error(e.getMessage());
			rs.setBody(Constant.ERROR);
		}
		return rs;

	}

}
