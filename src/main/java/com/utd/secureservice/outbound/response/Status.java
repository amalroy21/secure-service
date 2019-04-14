package com.utd.secureservice.outbound.response;

import java.util.List;

/**
 * this represents the status part of the response
 *
 */
public class Status {

	/**
	 * This represents the list of messages
	 */
	private List<Message> messages;
	
	/**
	 * Parameterized constructor
	 * @param messages
	 */
	public Status(List<Message> messages)
	{
		this.messages=messages;
	}
	
	/**
	 * This returns the messages
	 * @return messages
	 */
	public List<Message> getMessages() {
		return messages;
	}
	
	/**
	 * This sets the messages
	 * @param messages
	 */
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
}
