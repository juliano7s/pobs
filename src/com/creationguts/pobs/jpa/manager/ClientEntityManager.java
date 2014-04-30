package com.creationguts.pobs.jpa.manager;

import java.util.List;

import org.apache.log4j.Logger;

import com.creationguts.pobs.jpa.model.Client;

public class ClientEntityManager extends EntityManager<Client> {

	public List<Client> getClients() {
		logger.debug("Getting clients");
		this.getEntityManager().getTransaction().begin();
		List<Client> result = this.getEntityManager()
				.createQuery("from Client", Client.class).getResultList();
		logger.debug("total clients returned: " + result.size());
		this.getEntityManager().getTransaction().commit();
		this.closeEntityManager();

		return result;
	}

	public List<Client> getClientsByName(String name) {
		logger.debug("Getting clients by name: " + name);
		this.getEntityManager().getTransaction().begin();
		List<Client> result = this
				.getEntityManager()
				.createQuery(
						"from Client c where c.name like '" + name
								+ "%' order by c.name", Client.class)
				.getResultList();
		logger.debug("total clients returned: " + result.size());
		this.getEntityManager().getTransaction().commit();
		this.closeEntityManager();

		return result;
	}

	public Client getClientByCpf(String cpf) throws Exception {
		logger.debug("Getting clients by name: " + cpf);
		this.getEntityManager().getTransaction().begin();
		List<Client> result = this
				.getEntityManager()
				.createQuery("from Client c where c.cpf = '" + cpf + "'",
						Client.class).getResultList();
		logger.debug("total clients returned: " + result.size());
		this.getEntityManager().getTransaction().commit();
		this.closeEntityManager();

		if (result.size() > 1)
			throw new Exception("CPF should be unique on clients table");

		return result.get(0);
	}

	public Client getClientByPhone(String phone) throws Exception {
		logger.debug("Getting clients by phone: " + phone);
		this.getEntityManager().getTransaction().begin();
		List<Client> result = this
				.getEntityManager()
				.createQuery("from Client c where c.phone = '" + phone + "'",
						Client.class).getResultList();
		logger.debug("total clients returned: " + result.size());
		this.getEntityManager().getTransaction().commit();
		this.closeEntityManager();

		if (result.size() > 1)
			throw new Exception(
					"Phone number should be unique on clients table");
		else if (result.isEmpty())
			return null;

		return result.get(0);
	}

	public Client saveClient(Client client) {
		logger.debug("Saving client to the database: " + client);
		this.getEntityManager().getTransaction().begin();
		client = this.getEntityManager().merge(client);
		this.getEntityManager().getTransaction().commit();
		this.closeEntityManager();
		logger.debug(client + " saved to the database");
		return client;
	}

	public Client loadAll(Client client) {
		logger.debug("Loading attributes from client: " + client);
		this.getEntityManager().getTransaction().begin();
		client = this.getEntityManager().merge(client);
		if (client.getOrders() != null)
			client.getOrders().size(); // Force collection to load. See
										// http://stackoverflow.com/questions/21257947/reattaching-an-entity-to-lazy-load-a-collection-jpa-hibernate
		this.getEntityManager().getTransaction().commit();
		this.closeEntityManager();
		
		return client;
	}

	private static Logger logger = Logger.getLogger(ClientEntityManager.class);
}
