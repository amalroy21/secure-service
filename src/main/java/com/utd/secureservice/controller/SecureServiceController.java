package com.utd.secureservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.utd.secureservice.outbound.response.ApiResponse;
import com.utd.secureservice.service.ISecureService;

import java.security.NoSuchAlgorithmException;


/**
 * This Controller handles persisting vehicle Products to DB
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class SecureServiceController {
	/**
	 * Represents logger
	 */
    private static final Logger logger = LoggerFactory.getLogger(SecureServiceController.class);

    @Autowired
    private ISecureService secureService;
    
    /**
     * This controller helps to retrieve Packages and Products Data from the file.
     * @return ResponseEntity<Response>
     */
    @GetMapping(value = "/v1/getKeys")
    @ResponseBody
    public String getEncryptionKey(@RequestParam String userId) throws NoSuchAlgorithmException {
        long t1  = System.nanoTime();
    	logger.trace("Entering UploadProductsServiceController.processProducts() Method");
    	ResponseEntity<ApiResponse> responseEntity = null;

    	responseEntity = secureService.getEncryptionKey(userId);
        logger.trace("Exiting UploadProductsServiceController.processProducts() Method");
		System.out.println("get Keys:" + (System.nanoTime() - t1)/1000 + "micros");
        return (String) responseEntity.getBody().getPayload().getData();
    }
}