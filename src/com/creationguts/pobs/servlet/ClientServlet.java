package com.creationguts.pobs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.creationguts.pobs.manager.ClientEntityManager;

/**
 * Servlet implementation class ClientServlet
 */
public class ClientServlet extends HttpServlet {
	   
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
		logger.info("Calling servlet ClientServlet");
		ClientEntityManager cem = new ClientEntityManager();
		cem.getClients();
	}
	
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getRootLogger();
}
