package com.senla.hotel.dao.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.senla.hotel.dao.api.IRoomDao;
import com.senla.hotel.model.Room;
import com.senla.hotel.model.RoomGalery;
import com.senla.hotel.util.Constant;
@Repository
public class RoomDao extends AbstractDao<Room, Integer> implements IRoomDao {

	public RoomDao() {
		super(Room.class);

	}

	
	@SuppressWarnings("unchecked")
	public List<RoomGalery> getPhotos(Integer numRoom) {
		Criteria cr = createCriteria();
		cr.add(Restrictions.eq(Constant.ROOMID, numRoom));
		return cr.list();

	}
	
	

}
