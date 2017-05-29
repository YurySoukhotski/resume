package com.senla.hotel.service.api;

import java.util.List;

import com.senla.hotel.model.News;
import com.senla.hotel.model.Response;

public interface IManagerNews {

	public Response addNews(News news);

	public List<News> getNews();

	public Response updateNews(News news);

	public Response deleteNews(News news);

	

	
}
