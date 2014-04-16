package com.creationguts.pobs.servlet;

import java.io.IOException; 
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.creationguts.pobs.model.Client;

/**
 * Servlet implementation class ClientServlet
 */
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("com.creationguts.pobs");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Client> result = entityManager.createQuery( "from Client", Client.class ).getResultList();
		for ( Client client : result ) {
		    System.out.println( "Client (" + client.getName() + ")" );
		}
		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
