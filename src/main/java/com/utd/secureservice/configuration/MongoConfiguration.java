package com.utd.secureservice.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Encapsulates MongoDB configuration
 */
@Configuration
public class MongoConfiguration {

	@Value("${mongodb.userinfo.collection.name:user-information}")
	private String userInformationCollectionName;

	/**
	 * Gets the user information collection name for MongoDB
	 *
	 * @return A String reflecting the MongoDb collection name
	 */
	@Bean
	public String userInformationCollectionName() {
		return userInformationCollectionName;
	}
}
