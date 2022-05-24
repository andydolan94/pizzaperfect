package com.andydolan94.pizzaperfect.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "pizza")
@SequenceGenerator(name = "PIZZA_SEQUENCE", sequenceName = "pizza_sequence")
public class Pizza {

	@Id
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "PIZZA_SEQUENCE"
	)
	private long id;

	private String note;
	private String topping;
	private String base;
	private String size;

	@ManyToOne
	@JoinColumn(
		name = "pizza_order_id",
		insertable = false,
		updatable = false,
		nullable = false
	)
	private PizzaOrder pizzaOrder;

	@Column(name = "pizza_order_id")
	private long pizzaOrderId;

	/**
	 * Class constructor
	 */
	public Pizza() {}

	/**
	 * Class constructor defining the note and the order the pizza belongs to
	 * @param topping the topping to go on the pizza
	 * @param base the kind of bread that is added
	 * @param size the size of the pizza
	 * @param note the note written by a customer about the pizza
	 * @param pizzaOrderId the order id the pizza belongs to
	 */
	public Pizza(
		String topping,
		String base,
		String size,
		String note,
		Long pizzaOrderId
	) {
		this.topping = topping;
		this.base = base;
		this.size = size;
		this.note = note;
		this.pizzaOrderId = pizzaOrderId;
	}

	/**
	 * Class constructor defining the id, the note, and the order the pizza belongs to
	 * @param id the id of the pizza
	 * @param topping the topping to go on the pizza
	 * @param base the kind of bread that is added
	 * @param size the size of the pizza
	 * @param note the note written by a customer about the pizza
	 * @param pizzaOrderId the order id the pizza belongs to
	 */
	public Pizza(
		Long id, 
		String topping,
		String base,
		String size,
		String note, 
		Long pizzaOrderId
	) {
		this.id = id;
		this.topping = topping;
		this.base = base;
		this.size = size;
		this.note = note;
		this.pizzaOrderId = pizzaOrderId;
	}

	/**
	 * Gets the id of a pizza
	 * @return the pizza id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id of a pizza
	 * @param id the pizza id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the topping
	 * @return the topping
	 */
	public String getTopping() {
		return topping;
	}

	/**
	 * Sets the topping
	 * @param note the topping to set
	 */
	public void setTopping(String topping) {
		this.topping = topping;
	}

	/**
	 * Gets the base
	 * @return the base
	 */
	public String getBase() {
		return base;
	}

	/**
	 * Sets the base
	 * @param note the base to set
	 */
	public void setBase(String base) {
		this.base = base;
	}

	/**
	 * Gets the size
	 * @return the size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * Sets the size
	 * @param note the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * Gets the note written by a customer about the pizza
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * Sets the note written by a customer about the pizza
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * Gets the order the pizza belongs to
	 * @return the pizza order
	 */
	public long getPizzaOrderId() {
		return pizzaOrderId;
	}

	/**
	 * Sets the order for the pizza to belong to
	 * @param pizzaOrder the pizzaOrder to set
	 */
	public void setPizzaOrderId(long pizzaOrderId) {
		this.pizzaOrderId = pizzaOrderId;
	}
}
