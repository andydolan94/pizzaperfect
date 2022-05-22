 package com.andydolan94.pizzaperfect.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Topping {

	@Id
	@GeneratedValue
	private long id;

	private String name;
	private String description;

	/**
	 * Class constructor
	 */
	public Topping() {}

	/**
	 * Class constructor defining the name and the description of a topping.
	 * @param name the topping name
	 * @param description the topping description
	 */
	public Topping(String name, String description) {
		this.name = name;
		this.description = description;
	}

	/**
	 * Class Constructor defining the id, name, and description of the topping.
	 * @param id the topping id
	 * @param name the topping name
	 * @param description the topping description
	 */
	public Topping(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	/**
	 * Gets the id of a topping
	 * @return the topping id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id of a topping
	 * @param id the topping id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the name of a topping
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of a topping
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description of a topping
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of a topping
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
