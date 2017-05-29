package com.senla.hotel.service.api;

import java.util.List;

import com.senla.hotel.model.Country;
import com.senla.hotel.model.Response;

public interface IManagerCountry {

	public Response addCountry(Country cntr) ;

	public Response changeCountry(Country cntr);

	public List<Country> getCountries();

	

}
