package com.senla.hotel.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.senla.hotel.model.CustomerService;
import com.senla.hotel.model.Orders;
import com.senla.hotel.model.Response;
import com.senla.hotel.model.Room;
import com.senla.hotel.model.SimpleOrder;
import com.senla.hotel.model.SimpleRoomService;
import com.senla.hotel.service.api.IManagerOrder;
import com.senla.hotel.util.Constant;
import com.senla.hotel.util.OrdersAdapter;

@Controller
@RestController
public class OrderController {

	@Autowired
	IManagerOrder mng;

	@RequestMapping(value = Constant.URL_SERVICEORDERBYID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getServiceByOrder(@PathVariable(Constant.ID) int id) {
		List<CustomerService> list = mng.showInfoAboutServices(id);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting()
				.setDateFormat(Constant.DATEFORMAT).create();
		return new ResponseEntity<String>(gson.toJson(list), httpHeaders, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = Constant.URL_GUESTHISTBYID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getHistoryGuest(@PathVariable(Constant.ID) int id) {
		List<Orders> list = mng.showHistoryGuest(id);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting()
				.setDateFormat(Constant.DATEFORMAT).create();
		return new ResponseEntity<String>(gson.toJson(list), httpHeaders, HttpStatus.OK);
	}
	@RequestMapping(value = Constant.URL_SHEDULLER, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getSheduller() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.registerTypeAdapter(Orders.class, new OrdersAdapter()).create();
		List<Orders> listO = mng.getSheduller();
		return new ResponseEntity<String>(gson.toJson(listO), httpHeaders, HttpStatus.OK);
	}
	

	@RequestMapping(value = Constant.URL_LASTGUESTROOM, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getHistoryRoom(@PathVariable(Constant.ID) int id) {
		List<Orders> list = mng.showHistoryNumber(id);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting()
				.setDateFormat(Constant.DATEFORMAT).create();
		return new ResponseEntity<String>(gson.toJson(list), httpHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = Constant.URL_FREEROOMDATE, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getFreeRoomDate(@PathVariable(Constant.DATE) String date) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting()
				.setDateFormat(Constant.DATEFORMAT).create();
		List<Room> list = mng.showFreeNumbersDate(date);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<String>(gson.toJson(list), httpHeaders, HttpStatus.OK);
	}
	
	@RequestMapping(value = Constant.URL_COUNTGUESTDATE, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> getCountGuestDate(@PathVariable(Constant.DATE) String date) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Response>(mng.getCountGuest(date), httpHeaders, HttpStatus.OK);
	}
	

	@RequestMapping(value = Constant.URL_CLOSEORDER, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> closeOrder(@PathVariable(Constant.ID) int id) {
		Response res = mng.closeOrder(id);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Response>(res, httpHeaders, HttpStatus.OK);
	}

	@RequestMapping(value =Constant.URL_ORDERS, method = RequestMethod.GET)
	public ResponseEntity<String> listAll() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.registerTypeAdapter(Orders.class, new OrdersAdapter()).create();
		List<Orders> listO = mng.getOrders();
		return new ResponseEntity<String>(gson.toJson(listO), httpHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = Constant.URL_ORDERS, method = RequestMethod.POST)
	public ResponseEntity<Response> createOrder(@RequestBody SimpleOrder simpleO, UriComponentsBuilder ucBuilder) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Response>(mng.createOrder(simpleO), httpHeaders, HttpStatus.CREATED);

	}

	@RequestMapping(value = Constant.URL_ORDERS, method = RequestMethod.PUT)
	public ResponseEntity<Response> updateOrder(@RequestBody SimpleRoomService sr, UriComponentsBuilder ucBuilder) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Response>(mng.addServiceToOrder(sr), httpHeaders,	HttpStatus.OK);
	}

}
