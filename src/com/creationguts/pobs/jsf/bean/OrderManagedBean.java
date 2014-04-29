package com.creationguts.pobs.jsf.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.Logger;

import com.creationguts.pobs.jpa.manager.UserEntityManager;
import com.creationguts.pobs.jpa.model.Order;
import com.creationguts.pobs.jpa.model.User;

@ManagedBean
@RequestScoped
public class OrderManagedBean implements Serializable {
	
	@PostConstruct
	public void init() {
		this.newOrder = new Order();
		this.owners = (new UserEntityManager()).getUsers();
	}
	
	/**
	 * Action
	 * @return
	 */
	public String saveNewOrder() {
		logger.debug("Saving new order: ");
		for (User o : this.owners) {
			if (o.getId().equals(this.newOrderOwnerId)) {
				this.newOrder.setOwner(o);
			}
		}
		
		return "index";
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
	
	public Long getNewOrderOwnerId() {
		return this.newOrderOwnerId;
	}

	public void setNewOrderOwnerId(Long newOrderOwnerId) {
		this.newOrderOwnerId = newOrderOwnerId;
	}

	public List<User> getOwners() {
		return this.owners;
	}

	public void setOwners(List<User> owners) {
		this.owners = owners;
	}

	private Order newOrder;
	private Long newOrderOwnerId;
	private List<User> owners;
	
	@ManagedProperty(value="#{clientManagedBean}")
	private ClientManagedBean clientManagedBean;

	/**
	 * 
	 */
	private static final long serialVersionUID = 536149967322807306L;
	private static Logger logger = Logger.getLogger(OrderManagedBean.class);
}
