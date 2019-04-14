package com.utd.secureservice.controller.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.utd.secureservice.controller.SecureServiceController;

/**
 * This class represents the controller advice for
 * {@link UploadProductsServiceController}
 *
 */
@RestControllerAdvice(assignableTypes = { SecureServiceController.class })
public class SecureServiceControllerAdvice {

	/**
	 * Represents logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(SecureServiceControllerAdvice.class);

	/**
	 * Exception handler to handle {@link Exception}
	 * 
	 * @param Exception
	 * @return {@link ResponseEntity<Response>}
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception ex) {
		ResponseEntity<String> response = null;
		logger.error(ex.getMessage());
		return response;
	}

}
