package com.senla.hotel.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "listofservice")
public class CustomerService implements Serializable, Identified<Integer> {

	private static final long serialVersionUID = -1070159720804573286L;

	private static final String LISTID = "lists_id";
	private static final String SERVICEID = "service_id";
	private static final String ORDERID = "order_id";
	private static final String COUNTSERVICE = "count_service";
	private static final String SERVICENAME ="service_name";
	private static final String CURRENTSUM = "current_sum";
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = LISTID)
	@Expose
	private Integer id;

	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = SERVICEID)
	private RoomService roomService;

	@ManyToOne
	@JoinColumn(name = ORDERID)
	private Orders order;

	@Column(name = COUNTSERVICE)
	@Expose
	private Integer count;

	@Column(name = SERVICENAME)
	@Expose
	private String serviceName;
	
	@Column(name = CURRENTSUM)
	@Expose
	private Integer sum;

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RoomService getService() {
		return roomService;
	}

	public void setService(RoomService roomService) {
		this.roomService = roomService;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders orders) {
		this.order = orders;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	
	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}


}
