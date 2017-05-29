package com.senla.hotel.dao.service;

import org.springframework.stereotype.Repository;

import com.senla.hotel.dao.api.IRoomServiceDao;
import com.senla.hotel.model.RoomService;
@Repository
public class RoomServiceDao extends AbstractDao<RoomService, Integer> implements IRoomServiceDao{

	public RoomServiceDao() {
		super(RoomService.class);

	}

}
