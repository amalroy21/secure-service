package com.utd.secureservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.utd.secureservice.outbound.response.ApiResponse;
import com.utd.secureservice.outbound.response.Message;
import com.utd.secureservice.outbound.response.Status;
import com.utd.secureservice.service.ISecureService;

/**
 * This Service class provides method to Post Comments on a Product && method to
 * Update Product Status.
 *
 */
@Service
public class SecureServiceImpl implements ISecureService {

	private static final Logger logger = LoggerFactory.getLogger(SecureServiceImpl.class);


	/**
	 * This method performs logic to persist comments
	 * 
	 * @param commentsRequest {@link CommentsRequest}
	 * @param httpHeaders     {@link HttpHeaders}
	 * @return response {@link ResponseEntity<Response>}
	 */
	@Override
	public ResponseEntity<ApiResponse> getEncryptionKey(HttpHeaders httpHeaders) {

		logger.trace("Entering ProductsReviewServiceImpl.postComments() Method");
		Status status = null;
		ApiResponse apiResponse = null;
		List<Message> messages = new ArrayList<>();
		status = new Status(messages);
		apiResponse = new ApiResponse(status, null);
		ResponseEntity<ApiResponse> response = null;
		try {
			apiResponse.getStatus().getMessages().add(new Message("","",""));
			response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

		} catch (Exception exception) {
			throw exception;
		}

		logger.trace("Exiting ProductsReviewServiceImpl.postComments() Method");
		return response;
	}
}
