package com.creationguts.pobs.jpa.manager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import com.creationguts.pobs.Constants;
import com.creationguts.pobs.jpa.model.Client;

public class ClientEntityManager {
	
	public List<Client> getClients() {
		
		logger.debug("Getting clients");
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Constants.POBS_PERSISTENCE_UNIT);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Client> result = entityManager.createQuery( "from Client", Client.class ).getResultList();
		logger.debug("total clients returned: " + result.size());
		entityManager.getTransaction().commit();
		entityManager.close();
	
		return result;
	}
	
	private static Logger logger = Logger.getLogger(ClientEntityManager.class);
}
