 package com.andydolan94.pizzaperfect.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="BASE_SEQUENCE", sequenceName="base_sequence")
public class Base {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BASE_SEQUENCE")
	private long id;

	private String name;
	private String description;

	/**
	 * Class constructor
	 */
	public Base() {}

	/**
	 * Class constructor defining the name and the description of a base.
	 * @param name the base name
	 * @param description the base description
	 */
	public Base(String name, String description) {
		this.name = name;
		this.description = description;
	}

	/**
	 * Class Constructor defining the id, name, and description of the base.
	 * @param id the base id
	 * @param name the base name
	 * @param description the base description
	 */
	public Base(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	/**
	 * Gets the id of a base
	 * @return the base id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id of a base
	 * @param id the base id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the name of a base
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of a base
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description of a base
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of a base
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
