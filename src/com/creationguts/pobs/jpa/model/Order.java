package com.creationguts.pobs.jpa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(generator="order_inc")
	@GenericGenerator(name="order_inc", strategy="increment")
	public Long getOrderId() {
		return this.orderId;
	}
	public void setOrderId(Long id) {
		this.orderId = id;
	}
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="request_date")
	public Date getRequestDate() {
		return this.requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	@Column(name="delivery_date")
	public Date getDeliveryDate() {
		return this.deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	@Column(name="ready_date")
	public Date getReadyDate() {
		return this.readyDate;
	}
	public void setReadyDate(Date readyDate) {
		this.readyDate = readyDate;
	}
	public Float getValue() {
		return this.value;
	}
	public void setValue(Float value) {
		this.value = value;
	}
	public Float getCost() {
		return this.cost;
	}
	public void setCost(Float cost) {
		this.cost = cost;
	}
	public Boolean getDelivered() {
		return this.delivered;
	}
	public void setDelivered(Boolean delivered) {
		this.delivered = delivered;
	}
	public Boolean getReady() {
		return this.ready;
	}
	public void setReady(Boolean ready) {
		this.ready = ready;
	}
	
	@ManyToOne
	@JoinColumn(name="clientid", nullable=false)
	public Client getClient() {
		return this.client;
	}
	public void setClient(Client client) {
		this.client = client;
	}

	public User getUser() {
		return this.user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Transient
	public String getStatus() {
		if (this.getReady())
			return "Pronto";
		else if (this.getDelivered())
			return "Entregue";
		return "Em andamento";
	}
	
	@Transient
	public void setStatus(String status) {
		if (status.equals("Pronto")) {
			this.setReady(true);
			this.setDelivered(false);
		} else if (status.equals("Entregue")) {
			this.setReady(false);
			this.setDelivered(true);
		} else {
			this.setReady(false);
			this.setDelivered(false);
		}
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
	private User user;
}
