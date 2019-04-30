package com.utd.secureservice.outbound.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * This represents the response wrapper
 * @param <T>
 */
@JsonInclude(Include.NON_NULL)
public class Payload<T> {

	/**
	 * This represents the API response for publicKey
	 */
	@JsonInclude(Include.NON_NULL)
	private T data;

	public Payload(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
