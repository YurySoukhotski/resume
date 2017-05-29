package com.senla.hotel.service.api;

import com.senla.hotel.model.Configuration;
import com.senla.hotel.model.Response;

public interface IManagerCfg {

	public Configuration getConfig();

	public Response updateConfig(Configuration cfg);

}
