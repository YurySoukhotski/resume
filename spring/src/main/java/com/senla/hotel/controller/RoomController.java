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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.senla.hotel.model.Response;
import com.senla.hotel.model.Room;
import com.senla.hotel.service.api.IManagerRoom;
import com.senla.hotel.util.Constant;

@Controller
@RestController
public class RoomController {

	@Autowired
	IManagerRoom mng;

	
	@RequestMapping(value = Constant.URL_ROOM, method = RequestMethod.GET)
    public ResponseEntity<String> listAll() {
        List<Room> list = mng.showSortedRoom(Constant.ID, false);
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<String>(gson.toJson(list), httpHeaders, HttpStatus.OK);
    }
	
	
	@RequestMapping(value = Constant.URL_ROOM, method = RequestMethod.POST)
	public ResponseEntity<Response> createRoom(@RequestBody Room room, UriComponentsBuilder ucBuilder) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		return new ResponseEntity<Response>(mng.addNewRoom(room), httpHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(value = Constant.URL_ROOM, method = RequestMethod.PUT)
	public ResponseEntity<Response> updateRoom(@RequestBody Room room, UriComponentsBuilder ucBuilder) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Response>(mng.updateRoom(room), httpHeaders, HttpStatus.CREATED);
	}
	
	}
