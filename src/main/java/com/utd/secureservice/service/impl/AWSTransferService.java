package com.utd.secureservice.service.impl;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;

@Service
public class AWSTransferService {
	
	private static final Logger logger = LoggerFactory.getLogger(AWSTransferService.class);
	
	/**
	 * Represents AWS bucket name
	 */
	//@Value("${upload.vehicle.image.bucket.name.value}")
  	private String bucketName = "user-sensitive-docs";
	
	/**
	 * Represents AWS US key prefix
	 */
	//@Value("${upload.vehicle.image.key.prefix.usvalue}")
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
	
	/*public void uploadKeyToAws(File file) {
		String stringObjKeyName = "*** String object key name ***";
        String fileObjKeyName = "*** File object key name ***";
        String fileName = "*** Path to file to upload ***";

        try {
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard().build();
        
            // Upload a text string as a new object.
            //s3Client.putObject(bucketName, stringObjKeyName, "Uploaded String Object");
            
            // Upload a file as a new object with ContentType and title specified.
            PutObjectRequest request = new PutObjectRequest(bucketName, fileObjKeyName, new File(fileName));
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("plain/text");
            metadata.addUserMetadata("x-amz-meta-title", "someTitle");
            request.setMetadata(metadata);
            s3Client.putObject(request);
        }
        catch(AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process 
            // it, so it returned an error response.
            e.printStackTrace();
        }
        catch(SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            e.printStackTrace();
        }
	}*/
}
