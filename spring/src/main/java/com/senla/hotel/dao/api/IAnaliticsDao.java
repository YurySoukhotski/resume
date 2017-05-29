package com.senla.hotel.dao.api;

import java.util.Date;

import com.senla.hotel.model.Analitics;

public interface IAnaliticsDao {

	public Analitics getAnaliticsOnDate(Date date);
}
