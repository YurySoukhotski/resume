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
import com.senla.hotel.model.Configuration;
import com.senla.hotel.model.Response;
import com.senla.hotel.service.api.IManagerCfg;
import com.senla.hotel.util.Constant;

@Controller
@RestController
public class ConfigController {

	@Autowired
	IManagerCfg mng;

	@RequestMapping(value = Constant.URL_CONFIG, method = RequestMethod.GET)
    public ResponseEntity<Configuration> getAll() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        Configuration list = mng.getConfig();
        return new ResponseEntity<Configuration>(list, httpHeaders, HttpStatus.OK);
    }
	
	@RequestMapping(value = Constant.URL_CONFIG, method = RequestMethod.PUT)
	public ResponseEntity<Response> createNew(@RequestBody Configuration elem, UriComponentsBuilder ucBuilder) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Response>(mng.updateConfig(elem),httpHeaders, HttpStatus.CREATED);
	}
	
}
	