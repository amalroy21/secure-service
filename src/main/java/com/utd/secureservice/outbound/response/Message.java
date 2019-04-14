package com.utd.secureservice.outbound.response;

import java.util.Locale;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This represents the message that goes as part of Status
 *
 */
public class Message {

	private static final Logger logger = LoggerFactory.getLogger(Message.class);

	/**
	 * Represents Description
	 */
	private String description;

	/**
	 * Represents responseCode
	 */
	private String responseCode;

	/**
	 * Represents Detailed Description
	 */
	private String detailedDescription;

	/**
	 * Parameterized Constructor
	 * 
	 * @param description
	 * @param responseCode
	 * @param detailedDescription
	 */
	public Message(String description, String responseCode, String detailedDescription) {
		this.description = description;
		this.responseCode = responseCode;
		this.detailedDescription = detailedDescription;
	}

	/**
	 * This method returns the resourceBundle for specified locale
	 * @param locale
	 * @return resourceBundle
	 */
	private static ResourceBundle getResourceBundle(Locale locale) {
		logger.trace("Entering getResourceBundle()");
		ResourceBundle resourceBundle;
		resourceBundle = getResourceBundleForEnglish();
		return resourceBundle;
	}


	/**
	 * This method returns resourceBundle for English
	 * 
	 * @return resourceBundle
	 */
	private static ResourceBundle getResourceBundleForEnglish() {
		return ResourceBundle.getBundle("ErrorMessages", Locale.ENGLISH);
	}

	public static String getLocaleSpecificErrorMessage(Locale locale, String responseCode) {
		logger.trace("Entering getLocaleSpecificErrorMessage()");
		ResourceBundle bundle = getResourceBundle(locale);
		logger.trace("Exiting getLocaleSpecificErrorMessage()");
		return null != bundle ? bundle.getString(responseCode) : null;
	}

	/**
	 * This method returns the resourceBundle for specified property file
	 * @param propFilename
	 * @return resourceBundle
	 */
	private static ResourceBundle getResourceBundle(String propFilename) {
		logger.trace("Entering getResourceBundle()");
		return ResourceBundle.getBundle(propFilename);
		
	}

	/**
	 * This method returns the error message from specified property file for the error code passed
	 * @param propFilename
	 * @param errorCode
	 * @return String
	 */
	public static String getErrorMessage(String propFileName,String errorCode) {
		logger.trace("Entering getErrorMessage()");
		ResourceBundle bundle = getResourceBundle(propFileName);
		logger.trace("Exiting getErrorMessage()");
		return null != bundle ? bundle.getString(errorCode) : null;
	}
	
	/**
	 * This returns the description
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This sets the description
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * This returns the ResponseCode
	 * 
	 * @return responseCode
	 */
	public String getResponseCode() {
		return responseCode;
	}

	/**
	 * This sets the ResponseCode
	 * 
	 * @param responseCode
	 */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * This returns the DetailedDescription
	 * 
	 * @return detailedDescription
	 */
	public String getDetailedDescription() {
		return detailedDescription;
	}

	/**
	 * This sets the detailedDescription
	 * 
	 * @param detailedDescription
	 */
	public void setDetailedDescription(String detailedDescription) {
		this.detailedDescription = detailedDescription;
	}

	@Override
	public String toString() {
		return "Message [description=" + description + ", responseCode=" + responseCode + ", detailedDescription="
				+ detailedDescription + "]";
	}
	
	
}
