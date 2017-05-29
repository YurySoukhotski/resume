package com.senla.hotel.controller;

import java.util.List;
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

import com.senla.hotel.model.Country;
import com.senla.hotel.model.Response;
import com.senla.hotel.service.api.IManagerCountry;
import com.senla.hotel.util.Constant;

@Controller
@RestController
public class CountryController {

	@Autowired
	IManagerCountry mng;

	@RequestMapping(value = Constant.URL_COUNTRIES, method = RequestMethod.GET)
    public ResponseEntity<List<Country>> getAll() {
        List<Country> list = mng.getCountries();
        HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
        return new ResponseEntity<List<Country>>(list,httpHeaders, HttpStatus.OK);
    }
	@RequestMapping(value = Constant.URL_COUNTRIES, method = RequestMethod.PUT)
	public ResponseEntity<Response> update(@RequestBody Country elem, UriComponentsBuilder ucBuilder) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Response>(mng.changeCountry(elem),httpHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = Constant.URL_COUNTRIES, method = RequestMethod.POST)
	public ResponseEntity<Response> create(@RequestBody Country elem, UriComponentsBuilder ucBuilder) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Response>(mng.addCountry(elem),httpHeaders, HttpStatus.CREATED);
	}
}
	