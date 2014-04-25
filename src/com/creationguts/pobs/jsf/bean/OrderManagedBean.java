package com.creationguts.pobs.jsf.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.Logger;

import com.creationguts.pobs.jpa.model.Order;
import com.creationguts.pobs.jpa.model.User;

@ManagedBean
@RequestScoped
public class OrderManagedBean implements Serializable {
	
	/**
	 * Action
	 * @return
	 */
	public String saveNewOrder() {
		logger.debug("Saving new order: ");
		return null;
	}
	
	public ClientManagedBean getClientManagedBean() {
		return this.clientManagedBean;
	}

	public void setClientManagedBean(ClientManagedBean clientManagedBean) {
		this.clientManagedBean = clientManagedBean;
	}
	
	public Order getNewOrder() {
		return this.newOrder;
	}

	public void setNewOrder(Order newOrder) {
		this.newOrder = newOrder;
	}
	
	public List<User> getOwners() {
		return owners;
	}

	public void setOwners(List<User> owners) {
		this.owners = owners;
	}

	private Order newOrder;
	private List<User> owners;
	
	@ManagedProperty(value="#{clientManagedBean}")
	private ClientManagedBean clientManagedBean;

	/**
	 * 
	 */
	private static final long serialVersionUID = 536149967322807306L;
	private static Logger logger = Logger.getLogger(OrderManagedBean.class);
}
