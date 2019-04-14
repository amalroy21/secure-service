package com.utd.secureservice.outbound.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * This represents the API response 
 */
public class ApiResponse implements IResponse{

	/**
	 * Default constructor
	 */
	public ApiResponse() {
		super();
	}

	/**
	 * This represents the status section of the response
	 */
	private Status status;

	/**
	 * This represents the payload section of the response
	 */
	@SuppressWarnings("rawtypes")
	@JsonInclude(Include.NON_NULL)
	private Payload payload;

	/**
	 * parameterized Constructor 
	 * @param status
	 * @param payload
	 */
	public ApiResponse(Status status, @SuppressWarnings("rawtypes") Payload payload)
	{
		this.status=status;
		this.payload=payload;
	}

	/**
	 * This returns the status
	 * @return status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * This sets the status
	 * @param status
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * This returns the payload
	 * @return payload
	 */
	@SuppressWarnings("rawtypes")
	public Payload getPayload() {
		return payload;
	}

	/**
	 * This sets the payload
	 * @param payload
	 */
	public void setPayload(@SuppressWarnings("rawtypes") Payload payload) {
		this.payload = payload;
	}

}
