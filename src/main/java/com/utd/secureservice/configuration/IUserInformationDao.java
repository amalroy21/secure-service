package com.utd.secureservice.configuration;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.utd.secureservice.domain.UserInformation;


@Configuration("#{userInformationCollectionName}") 
public interface IUserInformationDao extends MongoRepository<UserInformation, String> {
	
	List<UserInformation> findByUserId(String userId);
}
 