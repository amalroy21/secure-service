package com.utd.secureservice.outbound.response;

import java.util.List;

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
	private List<String> publicKey;

	public List<String> getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(List<String> publicKey) {
		this.publicKey = publicKey;
	}
}
