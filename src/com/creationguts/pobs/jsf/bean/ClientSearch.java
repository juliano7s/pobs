package com.creationguts.pobs.jsf.bean;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.creationguts.pobs.jpa.manager.ClientEntityManager;
import com.creationguts.pobs.jpa.model.Client;

@ManagedBean
@RequestScoped
public class ClientSearch implements Serializable {

	/**
	 * Action
	 * 
	 * @return
	 */
	public String executeWholeSearch() {
		logger.debug("Executing whole client search.");
		logger.debug("Search string: " + this.wholeSearch);
		
		this.wholeSearch = this.wholeSearch.replace("-", "");
		this.wholeSearch = this.wholeSearch.replace(".", "");
		String view = "";

		Pattern phoneNumberPattern1 = Pattern.compile("\\d{2}|\\d{8}");
		Pattern cpfPattern1 = Pattern.compile("\\d{11}");

		ClientEntityManager cem = new ClientEntityManager();
		try {
			if (phoneNumberPattern1.matcher(this.wholeSearch).matches()) {
				logger.debug("wholeSearch matched phoneNumberPattern1 "
						+ phoneNumberPattern1);
				this.client = cem.getClientByPhone(this.wholeSearch);
				view = "client";
			} else if (cpfPattern1.matcher(this.wholeSearch).matches()) {
				logger.debug("wholeSearch matched cpfPattern1 " + cpfPattern1);
				this.client = cem.getClientByCpf(this.wholeSearch);
				view = "client";
			} else {
				logger.debug("wholeSearch matched no pattern.");
				this.clients = cem.getClientsByName(this.wholeSearch);
				view = "clients";
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			view = "index";
			UIComponent mainSearchButton = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance());
			FacesContext.getCurrentInstance().addMessage(
					mainSearchButton.getClientId(FacesContext
							.getCurrentInstance()),
					new FacesMessage(e.getMessage()));
		}
		
		if (this.client == null && this.clients == null) {
			logger.debug("Client not found, going to new client view");
			view = "newclient";
		}

		return view;
	}

	public String getWholeSearch() {
		return this.wholeSearch;
	}

	public void setWholeSearch(String search) {
		this.wholeSearch = search;
	}

	public List<Client> getClients() {
		return this.clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public Client getClient() {
		return this.client;
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
