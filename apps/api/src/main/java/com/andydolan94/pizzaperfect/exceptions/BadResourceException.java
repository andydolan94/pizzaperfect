package com.andydolan94.pizzaperfect.exceptions;

import java.util.ArrayList;
import java.util.List;

public class BadResourceException extends Exception {

	private List<String> errorMessages = new ArrayList<>();

	public BadResourceException() {}

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
