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
import com.senla.hotel.model.Document;
import com.senla.hotel.model.Response;
import com.senla.hotel.service.api.IManagerDoc;
import com.senla.hotel.util.Constant;

@Controller
@RestController
public class DocumentController {

	@Autowired
	IManagerDoc mng;

	@RequestMapping(value = Constant.URL_DOCUMENNTS, method = RequestMethod.GET)
    public ResponseEntity<List<Document>> getAll() {
        List<Document> list = mng.getDocs();
        HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
        return new ResponseEntity<List<Document>>(list, httpHeaders, HttpStatus.OK);
    }
	
	@RequestMapping(value = Constant.URL_DOCUMENNTS, method = RequestMethod.PUT)
	public ResponseEntity<Response> update(@RequestBody Document elem, UriComponentsBuilder ucBuilder) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Response>(mng.changeDoc(elem),httpHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = Constant.URL_DOCUMENNTS, method = RequestMethod.POST)
	public ResponseEntity<Response> create(@RequestBody Document elem, UriComponentsBuilder ucBuilder) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Response>(mng.addDoc(elem),httpHeaders, HttpStatus.CREATED);
	}
	
	
}
	