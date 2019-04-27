package com.utd.secureservice.service.impl;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.*;

import com.utd.secureservice.domain.FormattedKey;
import com.utd.secureservice.outbound.response.Payload;
import com.utd.secureservice.util.JCryptionUtil;
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
	private static final Random random = new Random();
	public static Map<String, FormattedKey> userKeyMap = new HashMap();


	/**
	 * This method performs logic to persist comments
	 *
	 * @return response {@link ResponseEntity<ApiResponse>}
	 */
	@Override
	public ResponseEntity<ApiResponse> getEncryptionKey(String userId) throws NoSuchAlgorithmException {

		logger.trace("Entering ProductsReviewServiceImpl.postComments() Method");
		FormattedKey formattedKey = userKeyMap.get(userId);
		if(formattedKey != null) {
			ApiResponse apiResponse = new ApiResponse(new Status(new ArrayList<>()), new Payload<String>(formattedKey.publicKey));
			ResponseEntity<ApiResponse> response = null;

			apiResponse.getStatus().getMessages().add(new Message("", "", ""));
			response = new ResponseEntity<>(apiResponse, HttpStatus.OK);
			return response;
		}
		Status status = null;
		ApiResponse apiResponse = null;
		status = new Status(new ArrayList<>());

		JCryptionUtil jCryptionUtil = new JCryptionUtil();
		KeyPair keys = jCryptionUtil.generateKeypair(512);

		String e = JCryptionUtil.getPublicKeyExponent(keys);
		String n = JCryptionUtil.getPublicKeyModulus(keys);
		String md = String.valueOf(JCryptionUtil.getMaxDigits(512));

		StringBuffer output = new StringBuffer();
		output.append("{\"e\":\"");
		output.append(e);
		output.append("\",\"n\":\"");
		output.append(n);
		output.append("\",\"maxdigits\":\"");
		output.append(md);
		output.append("\"}");

		String key = output.toString().replaceAll("\r", "").replaceAll("\n", "").trim();

		userKeyMap.put(userId, new FormattedKey(key, keys));
		apiResponse = new ApiResponse(status, new Payload<String>(key));
		ResponseEntity<ApiResponse> response = null;
		apiResponse.getStatus().getMessages().add(new Message("","",""));
		response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

		return response;
	}
}
