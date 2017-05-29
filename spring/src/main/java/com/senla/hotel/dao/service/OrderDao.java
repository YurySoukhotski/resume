package com.senla.hotel.dao.service;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.senla.hotel.dao.api.IOrderDao;
import com.senla.hotel.model.CustomerService;
import com.senla.hotel.model.OrderStatus;
import com.senla.hotel.model.Orders;
import com.senla.hotel.util.Constant;

import org.hibernate.criterion.Order;

@Repository
public class OrderDao extends AbstractDao<Orders, Integer> implements IOrderDao {

	private static final String DATEST = "dateStart";
	private static final String DATEEND = "dateEnd";
	private static final String OSTATUS = "statusOrder";
	private static final String ROOMID = "room.id";
	private static final String GUESTID = "guest.id";
	private static final String ORDERID = "order.id";

	public OrderDao() {
		super(Orders.class);

	}

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<CustomerService> getInfobyService(Integer numOrd) throws Exception {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CustomerService.class)
				.add(Restrictions.eq(ORDERID, numOrd));
		return criteria.list();

	}

	@SuppressWarnings({ "unchecked" })
	public List<Orders> getLastOrders(Integer nNumber) {

		Criteria criteria = createCriteria().add(Restrictions.eq(ROOMID, nNumber)).addOrder(Order.desc(DATEEND));
		return criteria.list();

	}

	@SuppressWarnings("unchecked")
	public List<Orders> getFreeNumberOnDate(Date date) {
		Criteria criteria = createCriteria()
				.add(Restrictions.or(Restrictions.gt(DATEST, date), Restrictions.lt(DATEEND, date)))
				.add(Restrictions.eq(OSTATUS, OrderStatus.BOOKED));
		return criteria.list();

	}

	@SuppressWarnings("unchecked")
	public List<Orders> getHistGuest(Integer guestId) {
		Criteria criteria = createCriteria().add(Restrictions.eq(GUESTID, guestId)).addOrder(Order.desc(DATEEND));
		return criteria.list();

	}

	@SuppressWarnings("unchecked")
	public List<Orders> getListOrder() {
		Criteria criteria = createCriteria().addOrder(Order.asc(Constant.ID));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	} 

	@SuppressWarnings("unchecked")
	public List<Orders> getSheduller() {
		Criteria criteria = createCriteria()
		.add(Restrictions.eq(OSTATUS, OrderStatus.BOOKED));
		return criteria.list();
	}

	public Integer getCountGuestOnDate(Date date) {
		Criteria criteria = createCriteria()
				.add(Restrictions.and(Restrictions.le(DATEST, date),Restrictions.ge(DATEEND, date)));
		return criteria.list().size();
	}

}
