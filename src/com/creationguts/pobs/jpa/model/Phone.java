package com.creationguts.pobs.jpa.model;

import javax.persistence.Embeddable;

@Embeddable
public class Phone {
	
	public Phone() {
		this.type = "Celular";
	}
	
	public String getNumber() {
		return this.number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}

	private String type;
	private String number;
}
