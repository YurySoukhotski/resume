package com.senla.hotel.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senla.hotel.dao.api.IDocDao;
import com.senla.hotel.dao.service.AbstractDao;
import com.senla.hotel.model.Document;
import com.senla.hotel.model.Response;
import com.senla.hotel.service.api.IManagerDoc;
import com.senla.hotel.util.Constant;

@Service
@SuppressWarnings("unchecked")
@Transactional
public class ManagerDoc<ID> implements IManagerDoc {
	private static Logger log = LogManager.getLogger(ManagerDoc.class);

	@Autowired
	private IDocDao docDao;

	
	public List<Document> getDocs() {
		try {
			return ((AbstractDao<Document, Integer>) docDao).getAll(Constant.ID, false);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public Response changeDoc(Document doc) {
		Response rs = new Response();
		rs.setName(Constant.UPDATED);
		try {
			((AbstractDao<Document, Integer>) docDao).update(doc);
			rs.setBody(Constant.UPDATED);
		} catch (Exception e) {
			log.error(e.getMessage());
			rs.setBody(Constant.ERROR);
		}
		return rs;

	}

	@Override
	public Response addDoc(Document doc) {
		Response rs = new Response();
		rs.setName(Constant.SAVED);
		try {
			((AbstractDao<Document, Integer>) docDao).save(doc);
			rs.setBody(Constant.SAVED);
		} catch (Exception e) {
			log.error(e.getMessage());
			rs.setBody(Constant.ERROR);
		}
		return rs;

	}

}
