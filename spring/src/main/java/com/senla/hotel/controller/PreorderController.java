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
import com.senla.hotel.model.Preorder;
import com.senla.hotel.model.Response;
import com.senla.hotel.service.api.IManagerMail;
import com.senla.hotel.service.api.IManagerPreorder;
import com.senla.hotel.util.Constant;

@Controller
@RestController
public class PreorderController {

	@Autowired
	IManagerPreorder mng;

	@Autowired
	IManagerMail mailService;

	@RequestMapping(value = Constant.URL_PREORDER, method = RequestMethod.GET)
	public ResponseEntity<List<Preorder>> getList() {
		List<Preorder> list = mng.getListPreorder();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<List<Preorder>>(list, httpHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = Constant.URL_PREORDERID, method = RequestMethod.GET)
	public ResponseEntity<Preorder> getPreorderId(@PathVariable(Constant.ID) int id) {
		Preorder elem = mng.getPreorderById(id);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Preorder>(elem, httpHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = Constant.URL_ONLINEBOOKING, method = RequestMethod.POST)
	public ResponseEntity<Response> createPreorder(@RequestBody Preorder elem, UriComponentsBuilder ucBuilder) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Response>(mng.addPreorders(elem), httpHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = Constant.URL_PREORDERID, method = RequestMethod.DELETE)
	public ResponseEntity<Response> deletePreorder(@PathVariable(Constant.ID) int id) {
		Preorder pr = mng.getPreorderById(id);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		Preorder prDelete = mng.getPreorderById(id);
		mailService.sendEmail(prDelete);
		return new ResponseEntity<Response>(mng.deletePreorder(pr), httpHeaders, HttpStatus.NO_CONTENT);
	}

}
