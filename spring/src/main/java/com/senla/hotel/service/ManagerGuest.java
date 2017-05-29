package com.senla.hotel.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senla.hotel.dao.api.IGuestDao;
import com.senla.hotel.dao.service.AbstractDao;
import com.senla.hotel.model.Guest;
import com.senla.hotel.model.Response;
import com.senla.hotel.service.api.IManagerGuest;
import com.senla.hotel.util.Constant;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class ManagerGuest implements IManagerGuest {
	private static Logger log = LogManager.getLogger(ManagerGuest.class);

	@Autowired
	private IGuestDao guestDao;

	public List<Guest> getListGuest() {
		try {
			return ((AbstractDao<Guest, Integer>) guestDao).getAll(Constant.ID, false);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public Response addGuest(Guest guest) {
		Response rs = new Response();
		rs.setName(Constant.ADDGUEST);
		try {
			((AbstractDao<Guest,Integer>) guestDao).save(guest);
			rs.setBody(Constant.ADDDED);
		} catch (Exception e) {
			log.error(e.getMessage());
			rs.setBody(Constant.ERROR);
		}
		return rs;

	}

	public Response updateGuest(Guest guest) {
		Response rs = new Response();
		rs.setName(Constant.UPDATEENT);
		try {
			((AbstractDao<Guest,Integer>) guestDao).update(guest);
			rs.setBody(Constant.UPDATED);
		} catch (Exception e) {
			log.error(e.getMessage());
			rs.setBody(Constant.ERROR);
		}

		return rs;
	}

	public Response deleteGuest(Guest guest) {
		Response rs = new Response();
		rs.setName(Constant.DELETEENT);
		try {
			((AbstractDao<Guest,Integer>) guestDao).delete(guest);
			rs.setBody(Constant.DELETED);
		} catch (Exception e) {
			log.error(e.getMessage());
			rs.setBody(Constant.ERROR);
		}
		return rs;
	}

	public Guest getGuestById(String nom) {
		try {
			return ((AbstractDao<Guest,Integer>) guestDao).getById(Integer.valueOf(nom));
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
