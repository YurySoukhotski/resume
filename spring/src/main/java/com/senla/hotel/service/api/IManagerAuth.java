package com.senla.hotel.service.api;

import com.senla.hotel.model.Login;
import com.senla.hotel.model.Response;

public interface IManagerAuth {

	public Response loginUser(Login user);

	
}
