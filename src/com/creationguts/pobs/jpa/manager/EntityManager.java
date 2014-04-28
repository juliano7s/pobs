package com.creationguts.pobs.jpa.manager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.creationguts.pobs.Constants;

public abstract class EntityManager<T> {

	protected javax.persistence.EntityManager e;

	protected javax.persistence.EntityManager getEntityManager() {
		if (this.e == null) {
			EntityManagerFactory entityManagerFactory = Persistence
					.createEntityManagerFactory(Constants.POBS_PERSISTENCE_UNIT);
			javax.persistence.EntityManager entityManager = entityManagerFactory
					.createEntityManager();

			this.e = entityManager;
		}

		return this.e;
	}

	protected void closeEntityManager() {
		if (this.e != null) {
			this.e.close();
		}

		this.e = null;
	}

}
