package com.andydolan94.pizzaperfect.exceptions;

public class ResourceNotFoundException extends Exception {

	/**
	 * Class constructor
	 */
	public ResourceNotFoundException() {}

	/**
	 * Class constructor defining the message
	 * @param msg the message to set
	 */
	public ResourceNotFoundException(String msg) {
		super(msg);
	}
}
