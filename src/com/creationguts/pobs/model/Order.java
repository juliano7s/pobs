package com.creationguts.pobs.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.creationguts.pobs.model.Client;

@Entity
@Table(name = "orders")
public class Order {	
	
	@Id
	@GeneratedValue(generator="order_inc")
	@GenericGenerator(name="order_inc", strategy="increment")
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long id) {
		this.orderId = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="request_date")
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	@Column(name="delivery_date")
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	@Column(name="ready_date")
	public Date getReadyDate() {
		return readyDate;
	}
	public void setReadyDate(Date readyDate) {
		this.readyDate = readyDate;
	}
	public Float getValue() {
		return value;
	}
	public void setValue(Float value) {
		this.value = value;
	}
	public Float getCost() {
		return cost;
	}
	public void setCost(Float cost) {
		this.cost = cost;
	}
	public Boolean getDelivered() {
		return delivered;
	}
	public void setDelivered(Boolean delivered) {
		this.delivered = delivered;
	}
	public Boolean getReady() {
		return ready;
	}
	public void setReady(Boolean ready) {
		this.ready = ready;
	}
	
	@ManyToOne
	@JoinColumn(name="clientid", nullable=false)
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}

	private Long orderId;
	private String description;
	private Date requestDate;
	private Date deliveryDate;
	private Date readyDate;
	private Float value;
	private Float cost;
	private Boolean delivered;
	private Boolean ready;
	private Client client;
}
