package com.stock.oauth2.resourceserver.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stock.oauth2.resourceserver.constant.StockAnalyzerConstant;
import com.stock.oauth2.resourceserver.domain.MongoUserDetails;
import com.stock.oauth2.resourceserver.service.UserService;

/**
 * @author shriram
 *
 */
@RestController
public class UserController {

	/**
	 * 
	 */
	@Autowired
	UserService userService;

	/**
	 * Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@PreAuthorize(StockAnalyzerConstant.OAUTH)
	@PostMapping(path = "/admin/createUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createStrategy(@RequestBody MongoUserDetails mongoUser) {
		LOGGER.debug("Entering UserController.class createStrategy()");
		String response = userService.createUser(mongoUser);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

}
