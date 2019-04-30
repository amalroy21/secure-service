package com.utd.secureservice.controller;

import com.utd.secureservice.domain.FormattedKey;
import com.utd.secureservice.service.ISecureService;
import com.utd.secureservice.service.impl.SecureServiceImpl;
import com.utd.secureservice.util.JCryptionUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@Autowired
    private ISecureService secureService;
	
    @RequestMapping("/home")
    public String getHomePage(){
//        System.out.println("Request for home page");
        return "home.jsp";

    }

    @PostMapping("/submit")
    public ResponseEntity<String> postSubmitPage(@RequestBody String request,
                                                @RequestParam String userId) throws Exception {
        long t1 = System.nanoTime();
        System.out.println("Got Request for user:"+ request.toString());
        FormattedKey key = SecureServiceImpl.userKeyMap.get(userId);
        String sensitiveInfo="";
        if(key != null) {
            String ccInfo = request.split("&")[1].trim();
            sensitiveInfo = JCryptionUtil.decrypt(ccInfo.substring(0, ccInfo.length()-1), key.pair);
            String[] splits = sensitiveInfo.toString().split("~");

            System.out.println("\nEncrypted Data:" + ccInfo.substring(0, ccInfo.length()-1));
            System.out.println("CreditCard No" + ":" + splits[0]);
            System.out.println("Expiry" + ":" + splits[1]);
            System.out.println("CVV" + ":" + splits[2]);
            System.out.println("ZipCode" + ":" + splits[3]);
            
        }
        System.out.println("Decryption Time:" + (System.nanoTime() - t1)/1000 + "micros");
        
        secureService.persistSensitiveInfo(sensitiveInfo,userId);
        return new ResponseEntity<String>("PASS" , HttpStatus.OK);
    }

    @PostMapping("/handshake")
    public ResponseEntity<String> getHandshake(@RequestBody String request){
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
