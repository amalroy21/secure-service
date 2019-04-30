package com.utd.secureservice.domain;

public class UserInformation {
	
	private String userId;
	private String fileName;
	private FormattedKey key;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public FormattedKey getKey() {
		return key;
	}
	public void setKey(FormattedKey formattedKey) {
		this.key = formattedKey;
	}
}
