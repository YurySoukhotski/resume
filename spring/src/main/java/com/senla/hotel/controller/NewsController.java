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

import com.senla.hotel.model.News;
import com.senla.hotel.model.Response;
import com.senla.hotel.service.api.IManagerNews;
import com.senla.hotel.util.Constant;

@Controller
@RestController
public class NewsController {

	@Autowired
	IManagerNews mng;

	@RequestMapping(value = Constant.URL_NEWS, method = RequestMethod.GET)
    public ResponseEntity<List<News>> getAll() {
        List<News> list = mng.getNews();
        HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<List<News>>(list, httpHeaders, HttpStatus.OK);
    }
	
	
	@RequestMapping(value = Constant.URL_NEWS, method = RequestMethod.PUT)
	public ResponseEntity<Response> updateNews(@RequestBody News elem, UriComponentsBuilder ucBuilder) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Response>(mng.updateNews(elem), httpHeaders, HttpStatus.OK);
	}
	
	@RequestMapping(value = Constant.URL_NEWS, method = RequestMethod.POST)
	public ResponseEntity<Response> createNews(@RequestBody News elem, UriComponentsBuilder ucBuilder) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Response>(mng.addNews(elem), httpHeaders, HttpStatus.OK);
	}
	
	
}
	