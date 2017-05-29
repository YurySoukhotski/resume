package com.senla.hotel.dao.service;

import org.springframework.stereotype.Repository;

import com.senla.hotel.dao.api.ICustomerServiceDao;
import com.senla.hotel.model.CustomerService;

@Repository
public class CustomerServiceDao extends AbstractDao<CustomerService, Integer>  implements ICustomerServiceDao{

	public CustomerServiceDao() {
		super(CustomerService.class);

	}

}
