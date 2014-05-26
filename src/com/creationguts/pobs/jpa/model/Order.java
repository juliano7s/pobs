package com.creationguts.pobs.jpa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="request_date")
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	@Column(name="delivery_date")
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	@Column(name="ready_date")
	public Date getReadyDate() {
		return readyDate;
	}
	public void setReadyDate(Date readyDate) {
		this.readyDate = readyDate;
	}
	public Float getValue() {
		return value;
	}
	public void setValue(Float value) {
		this.value = value;
	}
	public Float getCost() {
		return cost;
	}
	public void setCost(Float cost) {
		this.cost = cost;
	}
	
	@ManyToOne
	@JoinColumn(name="clientid", nullable=false)
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}

	@ManyToOne
	@JoinColumn(name="ownerid")
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Enumerated(EnumType.STRING)
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", description=" + description
				+ ", requestDate=" + requestDate + ", deliveryDate="
				+ deliveryDate + ", readyDate=" + readyDate + ", status="
				+ status + ", value=" + value + ", cost=" + cost + ", owner="
				+ owner + "]";
	}
	
	public enum Status {
	    INPROGRESS("INPROGRESS"),
	    READY("READY"),
	    DELIVERED("DELIVERED");

	    private final String name;

	    private Status(String s) {
	        name = s;
	    }

		public boolean equalsName(String otherName) {
			return (otherName == null) ? false : name.equals(otherName);
		}
		
		public String getViewName() {
			if (equals(Status.INPROGRESS)) {
				return "Em andamento";
			} else if (equals(Status.READY)) {
				return "Pronto";
			} else if (equals(Status.DELIVERED)) {
				return "Entregue";
			} else {
				return "ERRO";
			}
		}

	    @Override
		public String toString(){
	       return name;
	    }
	}

	private Long id;
	private String description;
	private Date requestDate;
	private Date deliveryDate;
	private Date readyDate;
	private Status status = Status.INPROGRESS;
	private Float value;
	private Float cost;
	private Client client;
	private User owner;
	
	private static final long serialVersionUID = 1710642303009857896L;
}
