package com.senla.hotel.service;

import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senla.hotel.dao.api.IRoomDao;
import com.senla.hotel.dao.service.AbstractDao;
import com.senla.hotel.model.Response;
import com.senla.hotel.model.Room;
import com.senla.hotel.model.RoomGalery;
import com.senla.hotel.service.api.IManagerRoom;
import com.senla.hotel.util.Constant;

@Service
@SuppressWarnings("unchecked")
public class ManagerRoom implements IManagerRoom {

	@Autowired
	private IRoomDao roomDao;

	
	private static Logger log = LogManager.getLogger(ManagerRoom.class);

	@Transactional
	public List<Room> showSortedRoom(String type, Boolean isFree) {
				try {
			return ((AbstractDao<Room, Integer>) roomDao).getAll(type, isFree);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	@Transactional
	public Response addNewRoom(Room newR) {
		
		Response rs = new Response();
		rs.setName(Constant.SAVEENT);
		try {
			((AbstractDao<Room, Integer>) roomDao).save(newR);
			rs.setBody(Constant.ADDDED);
		} catch (Exception e) {
			log.error(e.getMessage());
			rs.setBody(Constant.ERROR);
		} 
		return rs;
	}

	@Transactional
	public Response updateRoom(Room oldR) {
		Response rs = new Response();
		rs.setName(Constant.UPDATEENT);
		try {
			((AbstractDao<Room, Integer>) roomDao).update(oldR);
			rs.setBody(Constant.UPDATED);
		} catch (Exception e) {
			log.error(e.getMessage());
			rs.setBody(Constant.ERROR);
		} 
		
		return rs;
	}

	@Transactional
	public List<RoomGalery> getPhotos(Integer numRoom) {
		try {
			return roomDao.getPhotos(numRoom);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		} 
		
	}

	public boolean addPhotoToRoom(RoomGalery photo) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
