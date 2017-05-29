package com.senla.hotel.service.api;

import java.util.List;

import com.senla.hotel.model.Analitics;
import com.senla.hotel.model.Response;

public interface IManagerAnalitics {

	public List<Analitics> getAnalitics();

	public Response addAnalitics(Analitics analiticsItem);

	

	

	
}
