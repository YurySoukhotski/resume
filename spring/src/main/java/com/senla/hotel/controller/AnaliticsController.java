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

import com.senla.hotel.model.Analitics;
import com.senla.hotel.model.Response;
import com.senla.hotel.service.api.IManagerAnalitics;
import com.senla.hotel.util.Constant;

@Controller
@RestController
public class AnaliticsController {

	@Autowired
	IManagerAnalitics mng;

	@RequestMapping(value = Constant.URL_ANALITICS, method = RequestMethod.GET)
    public ResponseEntity<List<Analitics>> getAll() {
        List<Analitics> list = mng.getAnalitics();
        HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
        return new ResponseEntity<List<Analitics>>(list, httpHeaders, HttpStatus.OK);
    }
	
	@RequestMapping(value = Constant.URL_ANALITICS, method = RequestMethod.POST)
	public ResponseEntity<Response> createNew(@RequestBody Analitics elem, UriComponentsBuilder ucBuilder) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Response>(mng.addAnalitics(elem),httpHeaders, HttpStatus.CREATED);
	}
}
	