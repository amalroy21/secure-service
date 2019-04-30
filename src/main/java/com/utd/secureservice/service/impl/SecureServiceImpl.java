package com.utd.secureservice.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import com.utd.secureservice.configuration.IUserInformationDao;
import com.utd.secureservice.domain.FormattedKey;
import com.utd.secureservice.domain.UserInformation;
import com.utd.secureservice.outbound.response.Payload;
import com.utd.secureservice.util.JCryptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	//private static final Random random = new Random();
	public static Map<String, FormattedKey> userKeyMap = new HashMap<>();

	@Autowired
	AWSTransferService aWSTransferService;
	
	@Autowired
	IUserInformationDao userInformationDao;
	/**
	 * This method performs logic to get Encryption Key for a user
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
	
	/**
	 * This method performs logic to get Encryption Key for a user
	 *
	 * @return response {@link ResponseEntity<ApiResponse>}
	 */
	@Override
	public ResponseEntity<ApiResponse> persistSensitiveInfo(String info, String userId){
		
		File file = null;
		try {
			file = getFile(info);
			aWSTransferService.uploadUserFileToAws(file);
			//FileOutputStream keyFile = getKeyFile(userKeyMap.get(userId));
			persistUserInfo(userId,file.getName());
		
		} catch (IOException e) {
			System.out.println("IO Error occured");
			e.printStackTrace();
		}
		Status status = new Status(new ArrayList<>());
		ApiResponse apiResponse = new ApiResponse(status, new Payload<String>(""));
		ResponseEntity<ApiResponse> response = null;
		apiResponse.getStatus().getMessages().add(new Message("","",""));
		response = new ResponseEntity<>(apiResponse, HttpStatus.OK);
		return response;
	}

	private void persistUserInfo(String userId, String fileName) {
		UserInformation userInformation = new UserInformation();
		userInformation.setUserId(userId);
		userInformation.setKey(userKeyMap.get(userId));
		userInformation.setFileName(fileName);
		userInformationDao.save(userInformation);
	}

	private File getFile(String info) throws IOException {
		String ext = "txt";
		String name = String.format("%s.%s", generateRandomFileName(), ext);
		File file = new File(name);
        if (file.createNewFile()){
          System.out.println("File is created!");
        }else{
          System.out.println("File already exists.");
        }
        FileWriter writer = new FileWriter(file);
        writer.write(info);
        writer.close();
        return file;
	}
	
	/*private FileOutputStream getKeyFile(FormattedKey key) throws IOException {
		String fileName = generateRandomFileName();
		FileOutputStream file = new FileOutputStream(fileName);
        ObjectOutputStream objectOut = new ObjectOutputStream(file);
        objectOut.writeObject(key);
        objectOut.close();
        System.out.println("The Object  was succesfully written to a file");
        return file;
	}*/
	
	private String generateRandomFileName() {
		Random rand=new Random();
	    String aToZ="ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; 
	    StringBuilder res=new StringBuilder();
	    for (int i = 0; i < 10; i++) {
	       int randIndex=rand.nextInt(aToZ.length()); 
	       res.append(aToZ.charAt(randIndex));            
	    }
	    return res.toString();
	}
}
