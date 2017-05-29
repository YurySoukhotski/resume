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
import com.senla.hotel.model.Response;
import com.senla.hotel.model.RoomService;
import com.senla.hotel.service.api.IManagerRoomService;
import com.senla.hotel.util.Constant;

@Controller
@RestController
public class ServiceController {

	@Autowired
	IManagerRoomService mng;

	
	@RequestMapping(value = Constant.URL_SERVICE, method = RequestMethod.GET)
    public ResponseEntity<List<RoomService>> listAll() {
        List<RoomService> list = mng.showSortedService();
        HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<List<RoomService>>(list, httpHeaders, HttpStatus.OK);
    }
	
	@RequestMapping(value = Constant.URL_SERVICE, method = RequestMethod.POST)
	public ResponseEntity<Response> createService(@RequestBody RoomService rsrv, UriComponentsBuilder ucBuilder) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Response>(mng.addNewService(rsrv), httpHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(value = Constant.URL_SERVICE, method = RequestMethod.PUT)
	public ResponseEntity<Response> updateService(@RequestBody RoomService rsrv, UriComponentsBuilder ucBuilder) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Response>(mng.changeService(rsrv), httpHeaders, HttpStatus.CREATED);
	}
	
	}
