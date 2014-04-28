package com.creationguts.pobs.jpa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
	
	@Id
	@GeneratedValue(generator="order_inc")
	@GenericGenerator(name="order_inc", strategy="increment")
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	@ManyToOne
	@JoinColumn(name="clientid", nullable=false)
	public Client getClient() {
		return this.client;
	}
	public void setClient(Client client) {
		this.client = client;
	}

	@ManyToOne
	@JoinColumn(name="ownerid")
	public User getOwner() {
		return this.owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getStatus() {
		String statusPt = "ERROR";
		if (this.status.equals("INPROGRESS")) {
			statusPt = "Em andamento";
		} else if (this.status.equals("READY")) {
			statusPt = "Pronto";
		} else if (this.status.equals("DELIVERED")) {
			statusPt = "Entregue";
		}
		
		return statusPt;
	}
	public void setStatus(String status) {
		if (status.equals("Em andamento")) {
			this.status = "INPROGRESS";
		} else if (status.equals("Pronto")) {
			this.status = "READY";
		} else if (status.equals("Entregue")) {
			this.status = "DELIVERED";
		} else {
			this.status = "INPROGRESS";
		}
	}

	private Long id;
	private String description;
	private Date requestDate;
	private Date deliveryDate;
	private Date readyDate;
	private String status;
	private Float value;
	private Float cost;
	private Client client;
	private User owner;
	
	private static final long serialVersionUID = 1710642303009857896L;
}
