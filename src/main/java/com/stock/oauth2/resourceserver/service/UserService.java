package com.stock.oauth2.resourceserver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;
import com.stock.oauth2.resourceserver.domain.MongoClientDetails;
import com.stock.oauth2.resourceserver.domain.MongoUser;
import com.stock.oauth2.resourceserver.domain.MongoUserDetails;

/**
 * @author shriram
 *
 */
@Service
public class UserService {

	/**
	 * Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	/**
	 * 
	 */
	@Autowired
	MongoTemplate mongoTemplate;

	/**
	 * @param userDetails
	 */
	public String createUser(MongoUserDetails userDetails) {
		LOGGER.debug("Entering UserService.class createUser()");
		String response="User Creasted SucessFully";
		try {
			MongoUser mongoUser = new MongoUser();
			mongoUser.setUsername(userDetails.getUsername());
			mongoUser.setPassword("{noop}" + userDetails.getPassword());
			mongoUser.setRoles(Sets.newHashSet(userDetails.getRole()));
			mongoTemplate.save(mongoUser,"user");
			MongoClientDetails clientDetails = new MongoClientDetails();
			clientDetails.setClientId(userDetails.getClientId());
			clientDetails.setClientSecret("{noop}" + userDetails.getClientSecret());
			clientDetails.setSecretRequired(true);
			clientDetails.setResourceIds(Sets.newHashSet(userDetails.getResourceIds()));
			clientDetails.setScope(Sets.newHashSet(userDetails.getScope()));
			clientDetails.setAuthorizedGrantTypes(Sets.newHashSet(userDetails.getAuthorizedGrantTypes()));
			clientDetails.setRegisteredRedirectUri(Sets.newHashSet(userDetails.getRegisteredRedirectUri()));
			clientDetails.setAuthorities(AuthorityUtils.createAuthorityList(userDetails.getRole()));
			clientDetails.setAccessTokenValiditySeconds(userDetails.getAccessTokenValiditySeconds());
			clientDetails.setRefreshTokenValiditySeconds(userDetails.getRefreshTokenValiditySeconds());
			clientDetails.setAutoApprove(false);
			mongoTemplate.save(clientDetails,"client_details");
		} catch (Exception e) {
			LOGGER.error("Entering UserService.class createUser()");
			response="Error Creating User";
			throw e;
		}
		return response;
	}

}
