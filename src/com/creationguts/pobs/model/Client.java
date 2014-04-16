package com.creationguts.pobs.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "clients")
public class Client {
	
	@Id
	@GeneratedValue(generator="client_inc")
	@GenericGenerator(name="client_inc", strategy="increment")
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long id) {
		this.clientId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@OneToMany(targetEntity=Order.class, mappedBy="client")	
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	private Long clientId;
	private String name;
	private String cpf;
	private String address;
	private String phone;
	private String email;
	private List<Order> orders;
}
