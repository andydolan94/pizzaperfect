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

	/**
	 * Class constructor
	 */
	public PizzaOrder() {}

	/**
	 * Class constructor specifying a customer name, and a delivery address 
	 * for the order
	 * @param customerName the customer name
	 * @param deliveryAddress the delivery address
	 */
	public PizzaOrder(String customerName, String deliveryAddress) {
		this.customerName = customerName;
		this.deliveryAddress = deliveryAddress;
	}

	/**
	 * Class constructor specifying a unique id, a customer name, and a
	 * delivery address for the order
	 * @param id the unique id
	 * @param customerName the customer name
	 * @param deliveryAddress the delivery address
	 */
	public PizzaOrder(Long id, String customerName, String deliveryAddress) {
		this.id = id;
		this.customerName = customerName;
		this.deliveryAddress = deliveryAddress;
	}

	/**
	 * Gets the id of an order
	 * @return the order id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id of an order
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the customer name for an order
	 * @return the customer name
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * Sets the customer name for an order
	 * @param customerName the customer name to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * Gets the customer's delivery address for an order
	 * @return the delivery address
	 */
	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	/**
	 * Sets the customer's delivery address for an order
	 * @param deliveryAddress the delivery address to set
	 */
	public void setDeliverAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
}
