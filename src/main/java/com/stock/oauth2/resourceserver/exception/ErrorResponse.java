/**
 * The ResourceServer Project, BSD License,Copyright (c) 2019
 * All rights reserved.
**/
package com.stock.oauth2.resourceserver.exception;

import java.util.List;

public class ErrorResponse {

	/**
	 * 
	 */
	private String message;

	/**
	 * 
	 */
	private List<String> details;

	public ErrorResponse(String message, List<String> details) {
		super();
		this.message = message;
		this.details = details;
	}

	/**
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return
	 */
	public List<String> getDetails() {
		return details;
	}

	/**
	 * @param details
	 */
	public void setDetails(List<String> details) {
		this.details = details;
	}

}
