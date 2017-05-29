package com.senla.hotel.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senla.hotel.dao.api.INewsDao;
import com.senla.hotel.dao.service.AbstractDao;
import com.senla.hotel.model.News;
import com.senla.hotel.model.Response;
import com.senla.hotel.service.api.IManagerNews;
import com.senla.hotel.util.Constant;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class ManagerNews implements IManagerNews {
	private static Logger log = LogManager.getLogger(ManagerNews.class);

	@Autowired
	private INewsDao newsDao;

	public Response addNews(News newNews) {
		Response rs = new Response();
		rs.setName(Constant.SAVEENT);
		try {
			((AbstractDao<News, Integer>) newsDao).save(newNews);
			rs.setBody(Constant.ADDDED);
		} catch (Exception e) {
			log.error(e.getMessage());
			rs.setBody(Constant.ERROR);
		}
		return rs;
	}

	
	public List<News> getNews() {
		try {
			return ((AbstractDao<News, Integer>) newsDao).getAll(Constant.ID, false);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}

	}

	public Response updateNews(News news) {

		Response rs = new Response();
		rs.setName(Constant.UPDATEENT);

		try {
			((AbstractDao<News, Integer>) newsDao).update(news);
			rs.setBody(Constant.UPDATED);
		} catch (Exception e) {
			log.error(e.getMessage());
			rs.setBody(Constant.ERROR);
		}
		return rs;
	}

	public Response deleteNews(News news) {
		Response rs = new Response();
		rs.setName(Constant.DELETEENT);

		try {
			((AbstractDao<News, Integer>) newsDao).delete(news);
			rs.setBody(Constant.DELETED);
		} catch (Exception e) {
			log.error(e.getMessage());
			rs.setBody(Constant.ERROR);
		}
		return rs;
	}

}
