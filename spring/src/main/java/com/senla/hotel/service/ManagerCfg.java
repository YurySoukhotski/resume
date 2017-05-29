package com.senla.hotel.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senla.hotel.dao.api.ICfgDao;
import com.senla.hotel.dao.service.AbstractDao;
import com.senla.hotel.model.Configuration;
import com.senla.hotel.model.Response;
import com.senla.hotel.service.api.IManagerCfg;
import com.senla.hotel.util.Constant;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class ManagerCfg implements IManagerCfg {
	private static Logger log = LogManager.getLogger(ManagerCfg.class);

	@Autowired
	private ICfgDao cfgDao;

	public Configuration getConfig() {
		try {
			return cfgDao.getCfg();
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}

	}

	public Response updateConfig(Configuration cfg) {
		Response rs = new Response();
		rs.setName(Constant.SAVECONFIG);
		try {
			((AbstractDao<Configuration, Integer>) cfgDao).update(cfg);
			rs.setBody(Constant.SAVED);
		} catch (Exception e) {
			log.error(e.getMessage());
			rs.setBody(Constant.ERROR);
		}
		return rs;

	}

}
