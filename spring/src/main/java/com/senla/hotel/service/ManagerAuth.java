package com.senla.hotel.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senla.hotel.dao.api.IAuthDao;
import com.senla.hotel.model.Login;
import com.senla.hotel.model.Response;
import com.senla.hotel.model.User;
import com.senla.hotel.service.api.IManagerAuth;
import com.senla.hotel.util.Constant;

@Service
@Transactional
public class ManagerAuth implements IManagerAuth {
	private static Logger log = LogManager.getLogger(ManagerAuth.class);


	@Autowired
	private IAuthDao auth;

	public Response loginUser(Login user) {
		Response rs = new Response();
		try {
			User u = auth.getUser(user.getName(), user.getPass());
			if (u != null) {
				rs.setName(u.getPersonalInfo());
				rs.setBody(u.getRoles().toString());
			} else {
				rs.setName(Constant.USERNOTFOUND);
				rs.setBody(Constant.USERNOTFOUND);
			}
			return rs;
		} catch (Exception e) {
			rs.setName(Constant.USERNOTFOUND);
			rs.setBody(Constant.USERNOTFOUND);
			log.error(e.getMessage());
			return rs;
		}

	}
}
