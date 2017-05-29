package com.senla.hotel.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.google.gson.annotations.Expose;
import com.senla.hotel.util.Calend;
import com.senla.hotel.util.Constant;

@Entity
@Table(name = "orders")
public class Orders implements Serializable, Identified<Integer> {

	private static final long serialVersionUID = 6289799463944979128L;
	private static final String IDGUEST ="idGuest";
	private static final String IDROOM = "idRoom";
	private static final String DATEST ="dateSt";
	private static final String DATEEND = "dateEnd";
	private static final String ORDERSTATUS = "orderstatus";
	private static final String ORDER = "order";
	
	
	
	@Id
	@Column
	@Expose
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = IDGUEST)
	@Expose
	private Guest guest;

	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = IDROOM)
	@Expose
	private Room room;

	@Temporal(TemporalType.DATE)
	@Column(name = DATEST)
	@Expose
	private Date dateStart;

	@Temporal(TemporalType.DATE)
	@Column(name = DATEEND)
	@Expose
	private Date dateEnd;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = ORDER)
	private List<CustomerService> customerServices = new ArrayList<CustomerService>();

	@Column
	@Expose
	private Integer price;

	@Column(name = ORDERSTATUS)
	@Enumerated(EnumType.STRING)
	@Expose
	private OrderStatus statusOrder;

	public List<CustomerService> getCustomerServices() {
		return customerServices;
	}

	public void setCustomerServices(List<CustomerService> customerServices) {
		this.customerServices = customerServices;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer orderId) {
		this.id = orderId;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Date getDataStart() {
		return dateStart;
	}

	public void setDataStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDataEnd() {
		return dateEnd;
	}

	public void setDataEnd(Date dataEnd) {
		this.dateEnd = dataEnd;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public OrderStatus getStatusOrder() {
		return statusOrder;
	}

	public void setStatusOrder(OrderStatus statusOrder) {
		this.statusOrder = statusOrder;
	}

	public String listServiceToString(List<RoomService> listService) {
		StringBuilder sb = new StringBuilder();
		for (RoomService s : listService) {
			sb.append(s.getId()).append(Constant.SEP);
		}
		return sb.toString();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(id).append(Constant.SEP).append(guest.toString()).append(Constant.SEP).append(room.toString())
				.append(Constant.SEP).append(Calend.getTextDate(dateStart)).append(Constant.SEP)
				.append(Calend.getTextDate(dateEnd)).append(Constant.SEP).append(price);
		return sb.toString();
	}

}
