package com.senla.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.senla.hotel.model.Login;
import com.senla.hotel.model.Response;
import com.senla.hotel.service.api.IManagerAuth;
import com.senla.hotel.util.Constant;

@Controller
@RestController
public class AuthController {

	@Autowired
	IManagerAuth mng;

	
	@RequestMapping(value = Constant.URL_AUTH, method = RequestMethod.POST)
	public ResponseEntity<Response> loginUser(@RequestBody Login user, UriComponentsBuilder ucBuilder) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Response>(mng.loginUser(user), httpHeaders, HttpStatus.OK);
	}
	/***
	 * 
	 * 
	 * Authorization for token 
	 */
	@RequestMapping(value = Constant.URL_AUTHORIZATION, method = RequestMethod.POST)
	public ResponseEntity<Response> loginUserToken(@RequestBody Login user, UriComponentsBuilder ucBuilder) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Response>(mng.loginUser(user), httpHeaders, HttpStatus.OK);
	}
		
}
	