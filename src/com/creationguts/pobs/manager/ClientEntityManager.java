package com.creationguts.pobs.manager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import com.creationguts.pobs.model.Client;

public class ClientEntityManager {
	
	public List<Client> getClients() {
		
		logger.info(this);
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("com.creationguts.pobs");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Client> result = entityManager.createQuery( "from Client", Client.class ).getResultList();
		logger.debug("total clients returned: " + result.size());
		entityManager.getTransaction().commit();
		entityManager.close();
	
		return result;
	}
	
	private static Logger logger = Logger.getRootLogger();

}
