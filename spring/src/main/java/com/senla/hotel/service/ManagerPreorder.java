package com.senla.hotel.service;

import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senla.hotel.dao.api.IPreorderDao;
import com.senla.hotel.dao.service.AbstractDao;
import com.senla.hotel.model.Preorder;
import com.senla.hotel.model.Response;
import com.senla.hotel.service.api.IManagerPreorder;
import com.senla.hotel.util.Constant;

@Service
@SuppressWarnings("unchecked")
public class ManagerPreorder implements IManagerPreorder {
	private static Logger log = LogManager.getLogger(ManagerPreorder.class);

	@Autowired
	private IPreorderDao preorderDao;

	@Transactional
	public Response addPreorders(Preorder preorders) {
		Response rs = new Response();
		rs.setName(Constant.SAVEENT);
		try {
			((AbstractDao<Preorder, Integer>) preorderDao).save(preorders);
			rs.setBody(Constant.ADDDED);
		} catch (Exception e) {
			log.error(e.getMessage());
			rs.setBody(Constant.ERROR);
		}
		return rs;

	}

	@Transactional
	public List<Preorder> getListPreorder() {
		try {
			return ((AbstractDao<Preorder, Integer>) preorderDao).getAll(Constant.ID, false);
		} catch (Exception e) {
			log.error(e.toString());
			return null;
		}
	}

	@Transactional
	public Preorder getPreorderById(Integer id) {
		try {
			return ((AbstractDao<Preorder, Integer>) preorderDao).getById(id);
		} catch (Exception e) {
			log.error(e.toString());
			return null;
		}
	}

	@Transactional
	public Response deletePreorder(Preorder preorder) {
		Response rs = new Response();
		rs.setName(Constant.DELETEENT);
		try {
			Preorder rj = ((AbstractDao<Preorder, Integer>) preorderDao).getById(preorder.getId());
			((AbstractDao<Preorder, Integer>) preorderDao).delete(rj);
			rs.setBody(Constant.DELETED);
		} catch (Exception e) {
			log.error(e.getMessage());
			rs.setBody(Constant.ERROR);
		}
		return rs;

	}

}
