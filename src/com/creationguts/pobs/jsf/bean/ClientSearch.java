package com.creationguts.pobs.jsf.bean;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.Logger;

import com.creationguts.pobs.jpa.manager.ClientEntityManager;
import com.creationguts.pobs.jpa.model.Client;

@ManagedBean
@RequestScoped
public class ClientSearch implements Serializable {

	/**
	 * Action
	 * @return
	 */
	public String executeWholeSearch() {
		logger.debug("Executing whole client search.");
		logger.debug("Search string: " + wholeSearch);
		
		String view = "index";

		Pattern phoneNumberPattern1 = Pattern.compile("\\d{8}");
		Pattern phoneNumberPattern2 = Pattern.compile("\\d{2}\\d{8}");
		Pattern phoneNumberPattern3 = Pattern.compile("\\d{2}-\\d{8}");
		Pattern cpfPattern1 = Pattern.compile("\\d{11}");
		Pattern cpfPattern2 = Pattern.compile("\\d{9}-d{2}");

		if (phoneNumberPattern1.matcher(wholeSearch).matches()) {
			logger.debug("wholeSearch matched phoneNumberPattern1 " + phoneNumberPattern1);
		} else if (phoneNumberPattern2.matcher(wholeSearch).matches()) {
			logger.debug("wholeSearch matched phoneNumberPattern2 " + phoneNumberPattern2);
		} else if (phoneNumberPattern3.matcher(wholeSearch).matches()) {
			logger.debug("wholeSearch matched phoneNumberPattern3 " + phoneNumberPattern3);
		} else if (cpfPattern1.matcher(wholeSearch).matches()) {
			logger.debug("wholeSearch matched cpfPattern1 " + cpfPattern1);
		} else if (cpfPattern2.matcher(wholeSearch).matches()) {
			logger.debug("wholeSearch matched cpfPattern2 " + cpfPattern1);
		} else {
			logger.debug("wholeSearch matched no pattern.");
			clients = (new ClientEntityManager()).getClientsByName(wholeSearch);
			view = "clients";
		}

		return view;
	}

	public String getWholeSearch() {
		return wholeSearch;
	}

	public void setWholeSearch(String search) {
		this.wholeSearch = search;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	private String wholeSearch;
	private List<Client> clients;
	private Client client;

	private static Logger logger = Logger.getLogger(ClientSearch.class);
	private static final long serialVersionUID = 2461829560777826670L;
}
