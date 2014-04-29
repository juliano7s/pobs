package com.creationguts.pobs.jsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.creationguts.pobs.jpa.manager.ClientEntityManager;
import com.creationguts.pobs.jpa.model.Client;

@ManagedBean
@SessionScoped
public class ClientManagedBean implements Serializable {

	/**
	 * Action: execute search for name, phone and cpf
	 */
	public String executeWholeSearch() {
		logger.debug("Executing whole client search.");
		logger.debug("Search string: " + this.wholeSearch);
		logger.debug("Clearing client and client list from bean");
		this.client = null;
		this.clients = null;

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
				for (Client c : this.clients) {
					logger.debug("Phones loaded for client " + c.getName());
					for (String p : c.getPhoneNumbers()) {
						logger.debug(p);
					}
				}
				view = "clients";
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			view = "index";
			UIComponent mainSearchButton = UIComponent
					.getCurrentComponent(FacesContext.getCurrentInstance());
			FacesContext.getCurrentInstance().addMessage(
					mainSearchButton.getClientId(FacesContext
							.getCurrentInstance()),
					new FacesMessage(e.getMessage()));
		}

		if (this.client == null && this.clients == null) {
			logger.debug("Client not found, going to new client view");
			this.newClient();
			view = "newclient";
		}

		return view;
	}

	/**
	 * Action: create new client to persist and redirect to newclient.xhtml
	 */
	@PostConstruct
	public void newClient() {
		this.client = new Client();
		this.phoneNumbers = new ArrayList<String>();
		this.phoneNumbers.add("");
	}

	/**
	 * Action: choose client to edit on client.xhtml
	 */
	public String editClient() {
		logger.debug("Editing client");
		Long clientId = Long.parseLong(FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get("clientId"));
		logger.debug("Id: " + clientId);

		for (Client c : this.clients) {
			if (c.getId() == clientId) {
				this.client = c;
				this.phoneNumbers = this.client.getPhoneNumbers();
				logger.debug("Phones loaded: size = " + this.phoneNumbers.size());
				for (String p : this.phoneNumbers) {
					logger.debug(p);
				}
			}
		}

		return "newclient";
	}

	/**
	 * Action: choose client to view on client.xhtml
	 */
	public String viewClient() {
		logger.debug("Viewing client");
		if (this.client == null || this.client != null
				&& this.client.getId() == 0) {
			Long clientId = Long.parseLong(FacesContext.getCurrentInstance()
					.getExternalContext().getRequestParameterMap()
					.get("clientId"));
			logger.debug("Id: " + clientId);

			for (Client c : this.clients) {
				if (c.getId().equals(clientId)) {
					this.client = c;
				}
			}
		}
		
		logger.debug("Client to view: " + this.client.getName());
		this.client = (new ClientEntityManager()).loadAll(this.client);

		return "client";
	}

	/**
	 * Action: save client to the database(insert/update)
	 */
	public String saveClient() {
		logger.debug("Saving client on the database");
		logger.debug("Phones submitted: " + this.phoneNumbers);
		for (String p : this.phoneNumbers) {
			logger.debug(p);
		}
		ClientEntityManager cem = new ClientEntityManager();
		this.client.setPhoneNumbers(this.phoneNumbers);
		cem.saveClient(this.client);

		UIComponent clientSaveButton = UIComponent
				.getCurrentComponent(FacesContext.getCurrentInstance());
		FacesContext.getCurrentInstance()
				.addMessage(
						clientSaveButton.getClientId(FacesContext
								.getCurrentInstance()),
						new FacesMessage("Client saved with id "
								+ this.client.getId()));

		return this.viewClient();
	}

	public String addPhoneNumber() {
		logger.debug("Adding phone number");
		this.phoneNumbers.add("");
		return "newclient";
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

	public List<String> getPhoneNumbers() {
		logger.debug("Getting phone numbers: size=" + this.phoneNumbers.size());
		return this.phoneNumbers;
	}

	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	private String wholeSearch;
	private List<Client> clients;
	private Client client;
	private List<String> phoneNumbers;

	private static Logger logger = Logger.getLogger(ClientManagedBean.class);
	private static final long serialVersionUID = 2461829560777826670L;
}
