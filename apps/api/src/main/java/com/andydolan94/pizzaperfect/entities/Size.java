 package com.andydolan94.pizzaperfect.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="SIZE_SEQUENCE", sequenceName="size_sequence")
public class Size {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIZE_SEQUENCE")
	private long id;

	private String name;
	private String description;

	/**
	 * Class constructor
	 */
	public Size() {}

	/**
	 * Class constructor defining the name and the description of a size.
	 * @param name the size name
	 * @param description the size description
	 */
	public Size(String name, String description) {
		this.name = name;
		this.description = description;
	}

	/**
	 * Class Constructor defining the id, name, and description of the size.
	 * @param id the size id
	 * @param name the size name
	 * @param description the size description
	 */
	public Size(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	/**
	 * Gets the id of a size
	 * @return the size id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id of a size
	 * @param id the size id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the name of a size
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of a size
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description of a size
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of a size
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
