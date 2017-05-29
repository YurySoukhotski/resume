package com.senla.hotel.dao.api;

import com.senla.hotel.model.User;

public interface IAuthDao {

	public User getUser(String name, String pass);
}
