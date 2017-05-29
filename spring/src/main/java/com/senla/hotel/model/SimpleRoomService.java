package com.senla.hotel.model;

public class SimpleRoomService {

	private Integer serviceId;

	private Integer orderId;

	private Integer count;

	private String service_name;
	
	private Integer current_sum;

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public Integer getCurrent_sum() {
		return current_sum;
	}

	public void setCurrent_sum(Integer current_sum) {
		this.current_sum = current_sum;
	}



}
