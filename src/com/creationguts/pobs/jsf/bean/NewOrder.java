package com.creationguts.pobs.jsf.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.Logger;

import com.creationguts.pobs.jpa.model.Client;
import com.creationguts.pobs.jpa.model.User;

@ManagedBean
@RequestScoped
public class NewOrder implements Serializable {
	
	/**
	 * Action
	 * @return
	 */
	public String saveNewOrder() {
		logger.debug("Saving new order: ");
		return null;
	}

	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getRequestDate() {
		return this.requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public Date getDeliveryDate() {
		return this.deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
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
	
	public List<User> getOwners() {
		return this.owners;
	}
	public void setOwners(List<User> owners) {
		this.owners = owners;
	}

	private String description;
	private Date requestDate;
	private Date deliveryDate;
	private Date readyDate;
	private Float value;
	private Float cost;
	private Client client;
	private User user;
	private List<User> owners;

	/**
	 * 
	 */
	private static final long serialVersionUID = 536149967322807306L;
	private static Logger logger = Logger.getLogger(NewOrder.class);
}
