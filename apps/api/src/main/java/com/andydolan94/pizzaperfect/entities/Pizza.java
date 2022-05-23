 package com.andydolan94.pizzaperfect.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="PIZZA_SEQUENCE", sequenceName="pizza_sequence")
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PIZZA_SEQUENCE")
	private long id;

	private String note;

	public Pizza() {}

	public Pizza(String note) {
		this.note = note;
	}

	public Pizza(Long id, String note) {
		this.id = id;
		this.note = note;
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
}
