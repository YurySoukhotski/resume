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

import com.senla.hotel.model.Guest;
import com.senla.hotel.model.Response;
import com.senla.hotel.service.api.IManagerGuest;
import com.senla.hotel.util.Constant;

@Controller
@RestController
public class GuestController {

	@Autowired
	IManagerGuest managerGuest;

	@RequestMapping(value = Constant.URL_GUEST, method = RequestMethod.GET)
	public ResponseEntity<List<Guest>> listAllUsers() {
		List<Guest> users = managerGuest.getListGuest();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<List<Guest>>(users, httpHeaders, HttpStatus.OK);
	}
	
	//  test get guest with token
	@RequestMapping(value = "/guesttoken/", method = RequestMethod.GET)
	public ResponseEntity<List<Guest>> listAllUsersToken() {
		List<Guest> users = managerGuest.getListGuest();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<List<Guest>>(users, httpHeaders, HttpStatus.OK);
	}
	
	

	@RequestMapping(value = Constant.URL_GUEST, method = RequestMethod.POST)
	public ResponseEntity<Response> createUser(@RequestBody Guest user, UriComponentsBuilder ucBuilder) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Response>(managerGuest.addGuest(user), httpHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(value = Constant.URL_GUEST, method = RequestMethod.PUT)
	public ResponseEntity<Response> updateUser(@RequestBody Guest user, UriComponentsBuilder ucBuilder) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Response>(managerGuest.updateGuest(user), httpHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = Constant.URL_GUESTID, method = RequestMethod.DELETE)
	public ResponseEntity<Response> deleteUser(@PathVariable(Constant.ID) long id) {
		Guest g = managerGuest.getGuestById(String.valueOf(id));
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Response>(managerGuest.deleteGuest(g), httpHeaders, HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = Constant.URL_GUESTID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Guest> getUser(@PathVariable(Constant.ID) long id) {
		Guest g = managerGuest.getGuestById(String.valueOf(id));
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Guest>(g, httpHeaders, HttpStatus.OK);
	}

}
