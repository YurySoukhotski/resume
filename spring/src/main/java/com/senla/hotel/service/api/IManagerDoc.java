package com.senla.hotel.service.api;

import java.util.List;

import com.senla.hotel.model.Document;
import com.senla.hotel.model.Response;

public interface IManagerDoc {

	public Response addDoc(Document cntr) ;

	public Response changeDoc(Document cntr);

	public List<Document> getDocs();


	

}
