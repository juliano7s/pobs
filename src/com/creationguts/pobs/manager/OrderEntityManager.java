package com.creationguts.pobs.manager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import com.creationguts.pobs.Constants;
import com.creationguts.pobs.model.Order;

public class OrderEntityManager {
	
	public List<Order> getOrders() {
		
		logger.debug("Getting orders");
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Constants.POBS_PERSISTENCE_UNIT);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Order> result = entityManager.createQuery( "from Order", Order.class ).getResultList();
		logger.debug("total orders returned: " + result.size());
		entityManager.getTransaction().commit();
		entityManager.close();
	
		return result;
	}
	
	private static Logger logger = Logger.getLogger(OrderEntityManager.class);
}
