package com.andydolan94.pizzaperfect.exceptions;

/**
 * Exception handler for resources that cannot be found
 */
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
