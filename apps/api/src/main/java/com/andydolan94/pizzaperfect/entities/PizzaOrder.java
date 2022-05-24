package com.andydolan94.pizzaperfect.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "pizza_order")
@SequenceGenerator(name="PIZZA_ORDER_SEQUENCE", sequenceName="pizza_order_sequence")
public class PizzaOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PIZZA_ORDER_SEQUENCE")
	private long id;

	private String customerName;
	private String deliveryAddress;

	@OneToMany(
		mappedBy = "pizzaOrder"
	)
	private List<Pizza> pizzas;

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

	/**
	 * Gets the list of pizzas on an order
	 * @return the list of pizzas
	 */
	public List<Pizza> getPizzas() {
		return this.pizzas;
	}

	/**
	 * Sets the list of pizzas on an order
	 * @param pizzas the list of pizzas to set
	 */
	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}
}
