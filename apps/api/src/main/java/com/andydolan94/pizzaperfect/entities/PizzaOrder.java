package com.andydolan94.pizzaperfect.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "pizza_order")
@SequenceGenerator(
	name = "PIZZA_ORDER_SEQUENCE",
	sequenceName = "pizza_order_sequence"
)
public class PizzaOrder {

	@Id
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "PIZZA_ORDER_SEQUENCE"
	)
	private long id;

	@Column(nullable = false)
	private String customerName;

	@Column(nullable = false)
	private String deliveryAddress;
	
	private boolean submitted;
	private boolean delivered;

	@OneToMany(mappedBy = "pizzaOrder")
	private List<Pizza> pizzas;

	/**
	 * Class constructor
	 */
	public PizzaOrder() {}

	/**
	 * Class constructor specifying a customer name, and a delivery address
	 * for the order
	 * @param customerName the customer name
	 * @param deliveryAddress the delivery address of the customer
	 * @param submitted whether the customer is done with the order and has submitted it
	 * @param delivered whether the delivery driver has delivered the order
	 */
	public PizzaOrder(
		String customerName,
		String deliveryAddress,
		boolean submitted,
		boolean delivered
	) {
		this.customerName = customerName;
		this.deliveryAddress = deliveryAddress;
		this.submitted = submitted;
		this.delivered = delivered;
	}

	/**
	 * Class constructor specifying a unique id, a customer name, and a
	 * delivery address for the order
	 * @param id the unique id
	 * @param customerName the customer name
	 * @param deliveryAddress the delivery address of the customer
	 * @param submitted whether the customer is done with the order and has submitted it
	 * @param delivered whether the delivery driver has delivered the order
	 */
	public PizzaOrder(
		Long id,
		String customerName,
		String deliveryAddress,
		boolean submitted,
		boolean delivered
	) {
		this.id = id;
		this.customerName = customerName;
		this.deliveryAddress = deliveryAddress;
		this.submitted = submitted;
		this.delivered = delivered;
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

	/**
	 * Gets the submitted status of the order
	 * @return the submitted status
	 */
	public boolean getSubmitted() {
		return submitted;
	}

	/**
	 * Sets the submitted status of the order
	 * @param submitted the submitted status to set
	 */
	public void setSubmitted(boolean submitted) {
		this.submitted = submitted;
	}

	/**
	 * Gets the delivered status of the order
	 * @return the delivered status
	 */
	public boolean getDelivered() {
		return delivered;
	}

	/**
	 * Sets the delivered status of the order
	 * @param delivered the delivered status to set
	 */
	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}

	/**
	 * Gets the list of pizzas on an order
	 * @return the list of pizzas
	 */
	public List<Pizza> getPizzas() {
		return this.pizzas;
	}
}
