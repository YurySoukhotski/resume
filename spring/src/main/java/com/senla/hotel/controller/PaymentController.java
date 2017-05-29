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
import com.senla.hotel.model.Payment;
import com.senla.hotel.model.Response;
import com.senla.hotel.service.api.IManagerPayment;
import com.senla.hotel.util.Constant;

@Controller
@RestController
public class PaymentController {

	@Autowired
	IManagerPayment mng;

	@RequestMapping(value = Constant.URL_PAYMENTID, method = RequestMethod.GET)
	public ResponseEntity<List<Payment>> getPaymentByOrder(@PathVariable("id") int id) {
		List<Payment> list = mng.getPaymentsById(id);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<List<Payment>>(list, httpHeaders, HttpStatus.OK);
	}
	
	@RequestMapping(value = Constant.URL_PAYMENTDATE, method = RequestMethod.GET)
	public ResponseEntity<Response> getPaymentDate(@PathVariable("date") String date) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Response>(mng.getPaymentsByDate(date), httpHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = Constant.URL_PAYMENT, method = RequestMethod.POST)
	public ResponseEntity<Response> createNews(@RequestBody Payment elem, UriComponentsBuilder ucBuilder) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Response>(mng.addPayment(elem), httpHeaders, HttpStatus.OK);
	}

}
