package com.senla.hotel.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senla.hotel.dao.api.ICfgDao;
import com.senla.hotel.dao.api.ICustomerServiceDao;
import com.senla.hotel.dao.api.IGuestDao;
import com.senla.hotel.dao.api.IOrderDao;
import com.senla.hotel.dao.api.IRoomDao;
import com.senla.hotel.dao.api.IRoomServiceDao;
import com.senla.hotel.dao.service.AbstractDao;
import com.senla.hotel.dao.service.OrderDao;
import com.senla.hotel.model.Configuration;
import com.senla.hotel.model.CustomerService;
import com.senla.hotel.model.Guest;
import com.senla.hotel.model.OrderStatus;
import com.senla.hotel.model.Orders;
import com.senla.hotel.model.Response;
import com.senla.hotel.model.Room;
import com.senla.hotel.model.RoomService;
import com.senla.hotel.model.RoomStatus;
import com.senla.hotel.model.SimpleOrder;
import com.senla.hotel.model.SimpleRoomService;
import com.senla.hotel.service.api.IManagerOrder;
import com.senla.hotel.util.Calend;
import com.senla.hotel.util.Constant;

@Service
@SuppressWarnings("unchecked")
public class ManagerOrder implements IManagerOrder {

	public static Logger log = LogManager.getLogger(ManagerOrder.class);

	@Autowired
	private IOrderDao orderDao;

	@Autowired
	private IRoomServiceDao roomServiceDao;

	@Autowired
	private IGuestDao guestDao;

	@Autowired
	private ICfgDao cfgDao;

	@Autowired
	private IRoomDao roomDao;

	@Autowired
	private ICustomerServiceDao custDao;

	@Transactional
	public Response closeOrder(Integer ind) {
		Response rs = new Response();
		rs.setName(Constant.CLOSEORDER);
		Configuration cfg = cfgDao.getCfg();
		try {
			Orders newO = ((AbstractDao<Orders,Integer>) orderDao).getById(ind);
			List<CustomerService> listS = newO.getCustomerServices();
			Integer sum = 0;
			for (CustomerService cs : listS) {
				sum = sum + cs.getCount() * cs.getService().getsPrice();
			}
			sum = (int) (sum + (newO.getRoom().getPrice()
					* Calend.differenceBetweenDates(newO.getDataEnd(), newO.getDataStart())));

			if (newO.getGuest().getAmount() >= cfg.getDiscountLevelTen()) {
				sum = (int) (sum * 0.9);
			} else if ((newO.getGuest().getAmount() >= cfg.getDiscountLevelFive())
					&& ((newO.getGuest().getAmount() < cfg.getDiscountLevelTen()))) {
				sum = (int) (sum * 0.95);
			}

			newO.setPrice(sum);
			newO.setStatusOrder(OrderStatus.CLOSED);
			newO.getRoom().setStatus(RoomStatus.FREE);
			((AbstractDao<Orders,Integer>) orderDao).update(newO);
			rs.setBody(Constant.ORDERCLOSED);
		} catch (Exception e) {
			log.error(e.getMessage());
			rs.setBody(Constant.ERROR);
		}
		return rs;
	}

	@Transactional
	public List<Orders> showHistoryNumber(Integer nNumber) {
		try {
			return orderDao.getLastOrders(nNumber);
		} catch (Exception e) {
			log.error(e.toString());
			return null;
		}

	}

	@Transactional
	public List<Orders> showHistoryGuest(Integer guestId) {
		try {
			return orderDao.getHistGuest(guestId);
		} catch (Exception e) {
			log.error(e.toString());
			return null;
		}
	}

	@Transactional
	public List<CustomerService> showInfoAboutServices(Integer numOrd) {
		try {
			return orderDao.getInfobyService(numOrd);
		} catch (Exception e) {
			log.error(e.toString());
			return null;
		}
	}

	@Transactional
	public Response createOrder(SimpleOrder ord) {
		Response rs = new Response();
		rs.setName(Constant.SAVEENT);
		try {
			Orders newO = new Orders();
			newO.setDataStart(ord.getDateSt());
			newO.setDataEnd(ord.getDateEnd());
			newO.setPrice(0);
			// ------------------------------------------------
			newO.setGuest(((AbstractDao<Guest,Integer>) guestDao).getById(ord.getIdGuest()));
			newO.setRoom(((AbstractDao<Room,Integer>) roomDao).getById(ord.getIdRoom()));
			newO.getRoom().setStatus(RoomStatus.BOOKED);
			newO.setStatusOrder(OrderStatus.BOOKED);
			((AbstractDao<Orders,Integer>) orderDao).save(newO);
			rs.setBody(Constant.ADDDED);
		} catch (Exception e) {
			log.error(e.getMessage());
			rs.setBody(Constant.ERROR);
		}

		return rs;

	}

	@Transactional
	public Response addServiceToOrder(SimpleRoomService sr) {
		Response rs = new Response();
		rs.setName(Constant.SAVEENT);
		try {
			List<CustomerService> customerServices;
			Orders updateOrder = ((AbstractDao<Orders,Integer>) orderDao).getById(sr.getOrderId());
			if (updateOrder.getCustomerServices() == null) {
				customerServices = new ArrayList<CustomerService>();
			} else {
				customerServices = updateOrder.getCustomerServices();
			}

			CustomerService service = new CustomerService();
			service.setService(((AbstractDao<RoomService,Integer>) roomServiceDao).getById(sr.getServiceId()));
			service.setCount(sr.getCount());
			service.setSum(sr.getCurrent_sum());
			service.setServiceName(sr.getService_name());
			service.setOrder(updateOrder);
			customerServices.add(service);
			((AbstractDao<CustomerService,Integer>) custDao).save(service);
			updateOrder.setCustomerServices(customerServices);
			((AbstractDao<Orders,Integer>) orderDao).update(updateOrder);
			// ------------------------------------------------
			rs.setBody(Constant.ADDDED);
		} catch (Exception e) {
			log.error(e.getMessage());
			rs.setBody(Constant.ERROR);
		}
		return rs;

	}

	@Transactional
	public List<Orders> getOrders() {
		try {
			return orderDao.getListOrder();
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	@Transactional
	public List<Room> showFreeNumbersDate(String dateF) {
		try {
			List<Room> roomList = new ArrayList<Room>();
			roomList = ((AbstractDao<Room,Integer>) roomDao).getAll(Constant.ID, true);
			List<Orders> oList = orderDao.getFreeNumberOnDate(Calend.getDateText(dateF));
			if (oList != null) {
				for (Orders o : oList) {
					if (!roomList.contains(o.getRoom())) {
						roomList.add(o.getRoom());
					}
				}
			}
			return roomList;

		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	@Transactional
	public List<Orders> getSheduller() {
		try {
			return ((OrderDao) orderDao).getSheduller();
		} catch (Exception e) {
			log.error(e.toString());
			return null;
		}

	}

	@Transactional
	public Response getCountGuest(String date) {
		Response rs = new Response();
		rs.setName(Constant.COUNT);
		try {
			Integer count = ((OrderDao) orderDao).getCountGuestOnDate(Calend.getDateText(date));
			if (count != null) {
				rs.setBody(count.toString());
			} else {
				rs.setBody("0");
			}

			return rs;

		} catch (Exception e) {
			log.error(e.getMessage());
			rs.setBody(Constant.ERROR);
		}
		return rs;
	}

}
