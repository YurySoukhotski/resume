package com.senla.hotel.dao.service;

import org.springframework.stereotype.Repository;

import com.senla.hotel.dao.api.INewsDao;
import com.senla.hotel.model.News;
@Repository
public class NewsDao extends AbstractDao<News, Integer> implements INewsDao{

	public NewsDao() {
		super(News.class);
	}


}
