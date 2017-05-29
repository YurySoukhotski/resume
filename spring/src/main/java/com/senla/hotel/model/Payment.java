package com.senla.hotel.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "payment")
public class Payment implements Identified<Integer>{


	private static final String ORDERID = "order_id";
	private static final String DATE = "date";
	private static final String PAYTYPE = "payments_type";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name=ORDERID)
	private Integer order;

	@Temporal(TemporalType.DATE)
	@Column(name = DATE)
	private Date datePayment;

	
	@Column(name = PAYTYPE)
	@Enumerated(EnumType.STRING)
	private PaymentType paymentType;
	
	
	@Column
	private Integer summ;

	@Column
	private String comments;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Date getDatePayment() {
		return datePayment;
	}

	public void setDatePayment(Date datePayment) {
		this.datePayment = datePayment;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Integer getSumm() {
		return summ;
	}

	public void setSumm(Integer summ) {
		this.summ = summ;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	
	

}
