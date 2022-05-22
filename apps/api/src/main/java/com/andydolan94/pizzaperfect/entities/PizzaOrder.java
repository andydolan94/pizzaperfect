package com.andydolan94.pizzaperfect.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PizzaOrder {

	@Id
	@GeneratedValue
	private long id;

	private String customerName;
	private String deliveryAddress;

	public PizzaOrder() {}

	public PizzaOrder(String customerName, String deliveryAddress) {
		this.customerName = customerName;
		this.deliveryAddress = deliveryAddress;
	}

	public PizzaOrder(Long id, String customerName, String deliveryAddress) {
		this.id = id;
		this.customerName = customerName;
		this.deliveryAddress = deliveryAddress;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliverAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
}
