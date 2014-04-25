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
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory(Constants.POBS_PERSISTENCE_UNIT);
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		entityManager.getTransaction().begin();
		List<Client> result = entityManager.createQuery("from Client",
				Client.class).getResultList();
		logger.debug("total clients returned: " + result.size());
		entityManager.getTransaction().commit();
		entityManager.close();

		return result;
	}

	public List<Client> getClientsByName(String name) {
		logger.debug("Getting clients by name: " + name);
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory(Constants.POBS_PERSISTENCE_UNIT);
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		entityManager.getTransaction().begin();
		List<Client> result = entityManager.createQuery(
				"from Client c where c.name like '" + name
						+ "%' order by c.name", Client.class).getResultList();
		logger.debug("total clients returned: " + result.size());
		entityManager.getTransaction().commit();
		entityManager.close();

		return result;
	}

	public Client getClientByCpf(String cpf) throws Exception {
		logger.debug("Getting clients by name: " + cpf);
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory(Constants.POBS_PERSISTENCE_UNIT);
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		entityManager.getTransaction().begin();
		List<Client> result = entityManager.createQuery(
				"from Client c where c.cpf = '" + cpf + "'", Client.class)
				.getResultList();
		logger.debug("total clients returned: " + result.size());
		entityManager.getTransaction().commit();
		entityManager.close();
		
		if (result.size() > 1)
			throw new Exception("CPF should be unique on clients table");

		return result.get(0);
	}

	public Client getClientByPhone(String phone) throws Exception {
		logger.debug("Getting clients by phone: " + phone);
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory(Constants.POBS_PERSISTENCE_UNIT);
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		entityManager.getTransaction().begin();
		List<Client> result = entityManager.createQuery(
				"from Client c where c.phone = '" + phone + "'", Client.class)
				.getResultList();
		logger.debug("total clients returned: " + result.size());
		entityManager.getTransaction().commit();
		entityManager.close();
		
		if (result.size() > 1)
			throw new Exception("Phone number should be unique on clients table");
		else if (result.isEmpty())
			return null;

		return result.get(0);
	}

	public void saveClient(Client client) {
		logger.debug("Saving client to the database: " + client);
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory(Constants.POBS_PERSISTENCE_UNIT);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(client);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	private static Logger logger = Logger.getLogger(ClientEntityManager.class);
}
