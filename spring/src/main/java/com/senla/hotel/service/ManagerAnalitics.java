package com.senla.hotel.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senla.hotel.dao.api.IAnaliticsDao;
import com.senla.hotel.dao.service.AbstractDao;
import com.senla.hotel.model.Analitics;
import com.senla.hotel.model.Response;
import com.senla.hotel.service.api.IManagerAnalitics;
import com.senla.hotel.util.Constant;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class ManagerAnalitics implements IManagerAnalitics {
	private static Logger log = LogManager.getLogger(ManagerAnalitics.class);

	@Autowired
	private IAnaliticsDao analiticsDao;

	public List<Analitics> getAnalitics() {
		try {
			return ((AbstractDao<Analitics, Integer>) analiticsDao).getAll(Constant.ID, false);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}

	}

	public Response addAnalitics(Analitics analiticsItem) {
		Response rs = new Response();
		rs.setName(Constant.ADDNEWDATA);
		Analitics elem = analiticsDao.getAnaliticsOnDate(analiticsItem.getDate());
		
		try {
			if (elem !=null){
			elem.setCountGuest(analiticsItem.getCountGuest());	
			elem.setSummPeriod(analiticsItem.getSummPeriod());
			((AbstractDao<Analitics, Integer>) analiticsDao).update(elem);				
			} else {
			((AbstractDao<Analitics, Integer>) analiticsDao).save(analiticsItem);
			}
			rs.setBody(Constant.ADDDED);
		} catch (Exception e) {
			log.error(e.getMessage());
			rs.setBody(Constant.ERROR);
		}

		return rs;
	}

}
