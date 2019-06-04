package com.utd.secureservice.service.impl;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;

@Service
public class AWSTransferService {
	
	private static final Logger logger = LoggerFactory.getLogger(AWSTransferService.class);
	
	/**
	 * Represents AWS bucket name
	 */
  	private String bucketName = "user-sensitive-docs";
	
	/**
	 * Represents AWS US key prefix
	 */
  	private String keyPrefix;
	
	/**
	 * This method uploads User File with Sensitive Information to AWS S3 Bucket
	 * @return void {@link void>}
	 */
	public void uploadUserFileToAws(File file)
    {
		System.out.println("Entering AWSTransferUtil.uploadUserFileToAws() Method");
        TransferManager transferManager = TransferManagerBuilder.standard().build();
        keyPrefix = file.getName();
        try {
            Upload upload = transferManager.upload(bucketName, keyPrefix, file);
            upload.waitForCompletion();
            System.out.println("File is uploaded:"+keyPrefix);
            if(upload.isDone()) {
            	System.out.println("User Information uploaded to AWS S3 successfully");
            } else {
            	System.out.println("There is a problem while uploading User Information to AWS S3");
            }
        } catch (AmazonServiceException amazonServiceException) {
        	logger.error("Amazon service error {}"+ amazonServiceException.getMessage());
            System.exit(1);
        } catch (AmazonClientException amazonClientException) {
        	 logger.error("Amazon client error {}", amazonClientException.getMessage());
             System.exit(1);
        } catch (InterruptedException interruptedException) {
        	 logger.error("Transfer interrupted {}", interruptedException.getMessage());
        	 Thread.currentThread().interrupt();
             System.exit(1);
        }
        transferManager.shutdownNow();
        logger.trace("Exiting AWSTransferUtil.uploadUserFileToAws() Method");
    }
}
