package com.creationguts.pobs.jsf.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.creationguts.pobs.jpa.manager.ClientEntityManager;
import com.creationguts.pobs.jpa.manager.OrderEntityManager;
import com.creationguts.pobs.jpa.manager.UserEntityManager;
import com.creationguts.pobs.jpa.model.Order;
import com.creationguts.pobs.jpa.model.User;

@ManagedBean
@RequestScoped
public class OrderManagedBean implements Serializable {
	
	@PostConstruct
	public void init() {
		this.order = new Order();
		this.owners = (new UserEntityManager()).getUsers();
	}
	
	public String editOrder() {
		logger.debug("Editing order");
		Long orderId = Long.parseLong(FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get("orderId"));
		logger.debug("Id: " + orderId);
		
		for (Order o : this.clientManagedBean.getClient().getOrders()) {
			if (o.getId().equals(orderId))
				this.order = o;
		}
		
		return "edit_order";
	}
	
	/**
	 * Action
	 * @return
	 */
	public String saveOrder() {
		logger.debug("Saving new order: ");
		for (User o : this.owners) {
			if (o.getId().equals(this.orderOwnerId)) {
				this.order.setOwner(o);
			}
		}
		OrderEntityManager oem = new OrderEntityManager();
		this.order.setClient(this.clientManagedBean.getClient());
		oem.save(this.order);
		
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage("Pedido adicionado para o cliente "
						+ this.order.getClient().getName()));
		
		ClientEntityManager cem = new ClientEntityManager();
		this.clientManagedBean.setClient(cem.loadAll(this.order.getClient()));
		return "client";
	}
	
	public ClientManagedBean getClientManagedBean() {
		return this.clientManagedBean;
	}

	public void setClientManagedBean(ClientManagedBean clientManagedBean) {
		this.clientManagedBean = clientManagedBean;
	}
	
	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	public Long getOrderOwnerId() {
		return this.orderOwnerId;
	}

	public void setOrderOwnerId(Long orderOwnerId) {
		this.orderOwnerId = orderOwnerId;
	}

	public List<User> getOwners() {
		return this.owners;
	}

	public void setOwners(List<User> owners) {
		this.owners = owners;
	}

	private Order order;
	private Long orderOwnerId;
	private List<User> owners;
	
	@ManagedProperty(value="#{clientManagedBean}")
	private ClientManagedBean clientManagedBean;

	/**
	 * 
	 */
	private static final long serialVersionUID = 536149967322807306L;
	private static Logger logger = Logger.getLogger(OrderManagedBean.class);
}
