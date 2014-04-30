package com.creationguts.pobs.jpa.manager;

import java.util.List;

import org.apache.log4j.Logger;

import com.creationguts.pobs.jpa.model.Order;

public class OrderEntityManager extends EntityManager<Order> {
	
	public List<Order> getOrders() {
		
		logger.debug("Getting orders");
		this.getEntityManager().getTransaction().begin();
		List<Order> result = this.getEntityManager().createQuery( "from Order", Order.class ).getResultList();
		logger.debug("total orders returned: " + result.size());
		this.getEntityManager().getTransaction().commit();
		this.getEntityManager().close();
	
		return result;
	}
	
	private static Logger logger = Logger.getLogger(OrderEntityManager.class);
}
