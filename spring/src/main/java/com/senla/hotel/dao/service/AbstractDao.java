package com.senla.hotel.dao.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.senla.hotel.model.Identified;
import com.senla.hotel.model.RoomStatus;
import com.senla.hotel.util.Constant;

@Repository
public abstract class AbstractDao<T extends Identified<PK>, PK> {

	private Class<T> entityClass;
	
	@Autowired
	private SessionFactory sessionFactory;

	public AbstractDao(Class<T> entityClass) {

		this.entityClass = entityClass;
	}

	protected Criteria createCriteria() {

		return sessionFactory.getCurrentSession().createCriteria(entityClass);
	}

	@SuppressWarnings("unchecked")
	public T getById(Integer id) {
		Criteria criteria = createCriteria().add(Restrictions.eq(Constant.ID, id));
		criteria.uniqueResult();
		return (T) criteria.uniqueResult();

	}

	@SuppressWarnings("unchecked")
	public List<T> getAll(String type, Boolean isFree) {
		if (isFree) {
			Criteria criteria = createCriteria()
					.add(Restrictions.eq(Constant.STATUS, RoomStatus.FREE))
					.addOrder(Order.asc(type));
			return (List<T>) criteria.list();
		} else {
			Criteria criteria = createCriteria().addOrder(Order.asc(type));
			return criteria.list();
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		Criteria criteria = createCriteria();
		return criteria.list();
	}

	public void save(T object) {
		sessionFactory.getCurrentSession().save(object);

	}

	public void update(T object) {
		sessionFactory.getCurrentSession().update(object);

	}

	public void delete(T object) {
		sessionFactory.getCurrentSession().delete(object);

	}
}
