package com.andydolan94.pizzaperfect.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Exception handler for malformed data
 */
public class BadResourceException extends Exception {

	/**
	 * List for holding the error messages
	 */
	private List<String> errorMessages = new ArrayList<>();

	/**
	 * Class constructor
	 */
	public BadResourceException() {}

	/**
	 * Class constructor defining the message
	 * @param msg the message to set
	 */
	public BadResourceException(String msg) {
		super(msg);
	}

	/**
	 * Gets a list of error messages
	 * @return the errorMessages
	 */
	public List<String> getErrorMessages() {
		return errorMessages;
	}

	/**
	 * Sets a list of error messages
	 * @param errorMessages the errorMessages to set
	 */
	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	/**
	 * Adds an error message
	 * @param message the message to add
	 */
	public void addErrorMessage(String message) {
		this.errorMessages.add(message);
	}
}
