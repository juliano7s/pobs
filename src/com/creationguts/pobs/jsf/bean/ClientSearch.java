package com.creationguts.pobs.jsf.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

@ManagedBean
@ViewScoped
public class ClientSearch implements Serializable {

	public String executeWholeSearch() {
		logger.debug("Executing whole client search.");
		logger.debug("Search string: " + wholeSearch);

		return "index";
	}

	public String getWholeSearch() {
		return wholeSearch;
	}

	public void setWholeSearch(String search) {
		this.wholeSearch = search;
	}

	private String wholeSearch;
	
	private static Logger logger = Logger.getLogger(ClientSearch.class);
	private static final long serialVersionUID = 2461829560777826670L;
}
