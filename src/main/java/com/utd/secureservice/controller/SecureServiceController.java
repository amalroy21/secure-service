package com.utd.secureservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.utd.secureservice.outbound.response.ApiResponse;
import com.utd.secureservice.service.ISecureService;


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
     * @param MultipartFile
     * @return ResponseEntity<Response>
     */
    @GetMapping(value = "/v1/products")

    public ResponseEntity<ApiResponse> getEncryptionKey(HttpHeaders httpHeaders) {
    	logger.trace("Entering UploadProductsServiceController.processProducts() Method");
    	ResponseEntity<ApiResponse> responseEntity = null;
		try {
			responseEntity = secureService.getEncryptionKey(httpHeaders);
		} catch (Exception e) {
			logger.error("Error Occured while processing the uploaded file", e);
		}
        logger.trace("Exiting UploadProductsServiceController.processProducts() Method");
        return responseEntity;
    }
    
    
}