package com.senla.hotel.dao.service;

import org.springframework.stereotype.Repository;

import com.senla.hotel.dao.api.IPreorderDao;
import com.senla.hotel.model.Preorder;

@Repository
public class PreorderDao extends AbstractDao<Preorder, Integer> implements IPreorderDao{
	
public PreorderDao() {
		super(Preorder.class);

	}


	
	}
