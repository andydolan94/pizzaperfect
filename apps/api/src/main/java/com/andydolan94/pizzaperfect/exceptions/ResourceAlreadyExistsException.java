package com.andydolan94.pizzaperfect.exceptions;

public class ResourceAlreadyExistsException extends Exception {

	/**
	 * Class constructor
	 */
	public ResourceAlreadyExistsException() {}

	/**
	 * Class constructor defining the message
	 * @param msg the message to set
	 */
	public ResourceAlreadyExistsException(String msg) {
		super(msg);
	}
}
