package com.senla.hotel.dao.service;

import org.springframework.stereotype.Repository;

import com.senla.hotel.dao.api.IDocDao;
import com.senla.hotel.model.Document;
@Repository
public class DocDao extends AbstractDao<Document, Integer> implements IDocDao {

	public DocDao() {
		super(Document.class);
	}


}
