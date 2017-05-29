package com.senla.hotel.service;

import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.senla.hotel.dao.api.IRoomServiceDao;
import com.senla.hotel.dao.service.AbstractDao;
import com.senla.hotel.model.*;
import com.senla.hotel.service.api.IManagerRoomService;
import com.senla.hotel.util.Constant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@SuppressWarnings("unchecked")
public class ManagerRoomService implements IManagerRoomService {

	public static Logger log = LogManager.getLogger(ManagerRoomService.class);

	@Autowired
	private IRoomServiceDao serviceDao;

	@Transactional
	public List<RoomService> showSortedService() {
		try {
			return ((AbstractDao<RoomService, Integer>) serviceDao).getAll();
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	@Transactional
	public Response addNewService(RoomService newService) {
		Response rs = new Response();
		rs.setName(Constant.SAVEENT);
		try {
			((AbstractDao<RoomService, Integer>) serviceDao).save(newService);
			rs.setBody(Constant.ADDDED);
		} catch (Exception e) {
			log.error(e.getMessage());
			rs.setBody(Constant.ERROR);
		}
		return rs;

	}

	@Transactional
	public Response changeService(RoomService srv) {

		Response rs = new Response();
		rs.setName(Constant.UPDATEENT);
		try {
			((AbstractDao<RoomService, Integer>) serviceDao).update(srv);
			rs.setBody(Constant.UPDATED);
		} catch (Exception e) {
			log.error(e.getMessage());
			rs.setBody(Constant.ERROR);
		}
		return rs;

	}

	

}
