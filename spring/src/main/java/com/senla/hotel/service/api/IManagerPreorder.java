package com.senla.hotel.service.api;

import java.util.List;

import com.senla.hotel.model.Preorder;
import com.senla.hotel.model.Response;

public interface IManagerPreorder {

	public Response addPreorders(Preorder preorders);

	public List<Preorder> getListPreorder();

	public Preorder getPreorderById(Integer orderId);
	
	public Response deletePreorder(Preorder preorder);

	
}
