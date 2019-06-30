/**
 * The ResourceServer Project, BSD License,Copyright (c) 2019
 * All rights reserved.
**/
package com.stock.oauth2.resourceserver.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stock.oauth2.resourceserver.config.ErrorCodeMsgConstant;
import com.stock.oauth2.resourceserver.constant.StockAnalyzerConstant;
import com.stock.oauth2.resourceserver.domain.Dbcollection;
import com.stock.oauth2.resourceserver.domain.Stock;
import com.stock.oauth2.resourceserver.dto.OaitmDTO;
import com.stock.oauth2.resourceserver.exception.StockAnalyzerException;
import com.stock.oauth2.resourceserver.restClient.RestClient;
import com.stock.oauth2.resourceserver.service.StockDataAnalyzerService;
import com.stock.oauth2.resourceserver.util.CommonUtility;
import com.stock.oauth2.resourceserver.validator.CustomValidator;

/**
 * 
 * Controller to handle mongo crud opeation
 * 
 * 
 * @author Shriram
 *
 */
@RestController
public class StockDataAnalyzerController {

	/**
	 * StockDataAnalyzerservice
	 */
	@Autowired
	StockDataAnalyzerService stockDataAnalyzerService;

	/**
	 * REST Client object
	 */
	@Autowired
	RestClient restclient;

	/**
	 * 
	 */
	@Autowired
	CustomValidator validator;
	
	/**
	 * 
	 */
	@Autowired
	CommonUtility commonUtility;
	
	@Autowired
	ErrorCodeMsgConstant errormsgConfig;

	 public static final String ROLE_ADMIN = "ROLE_ADMIN";
	    public static final String ROLE_USER = "ROLE_USER";
	/**
	 * Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(StockDataAnalyzerController.class);
	/**
	 * Create the doc
	 * 
	 * @param request
	 * @return
	 * @throws StockAnalyzerException 
	 */
	@PreAuthorize(StockAnalyzerConstant.OAUTH)
	@PostMapping(path = "/createWatchList", consumes = StockAnalyzerConstant.CONTENTTYPE, produces = StockAnalyzerConstant.CONTENTTYPE)
	public ResponseEntity<String> createStrategy(@RequestBody String request) throws StockAnalyzerException {
		LOGGER.debug("Entering StockDataAnalyzerController.class createStrategy()");
        String isValid= commonUtility.validateJson(StockAnalyzerConstant.WATHLIST_SCHEMA, request);
        String response = null;
		if(StockAnalyzerConstant.ISTURE.equals(isValid)) {
		 response= stockDataAnalyzerService.createWatchList(request);
		}else {
			response=isValid;
		}
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	/**
	 * Create the doc
	 * 
	 * @param strategy
	 * @return
	 * @throws StockAnalyzerException 
	 */
	@PreAuthorize(StockAnalyzerConstant.OAUTH)
	@PostMapping(path = "/createCollection", consumes = StockAnalyzerConstant.CONTENTTYPE, produces = StockAnalyzerConstant.CONTENTTYPE)
	public ResponseEntity<String> createCollection(@RequestBody Dbcollection collection) throws StockAnalyzerException {
		LOGGER.debug("Entering StockDataAnalyzerController.class createCollection()");
		stockDataAnalyzerService.createCollection(collection);
		return new ResponseEntity<String>("", HttpStatus.OK);
	}
	/**
	 * Find OTM,ATM,ITM
	 * 
	 * @param strategy
	 * @return
	 * @throws JSONException
	 * @throws StockAnalyzerException 
	 */
	@PreAuthorize(StockAnalyzerConstant.OAUTH)
	@PostMapping(path = "/FetchSymbolDataFromGdfAPI", consumes = StockAnalyzerConstant.CONTENTTYPE, produces = StockAnalyzerConstant.CONTENTTYPE)
	public ResponseEntity<?> createOaitmFetchDataFromGdfAPI(@RequestBody OaitmDTO oaitmDTO) throws JSONException, StockAnalyzerException {
		LOGGER.debug("Entering StockDataAnalyzerController.class findOAITM()");
		String isValid =commonUtility.checkRequest(validator, oaitmDTO, "OaitmDTO");
		String response="";
		if(StockAnalyzerConstant.ISTURE.equals(isValid)){
			 response = stockDataAnalyzerService.createOAITMInvokeGlobalDataFeedAPI(oaitmDTO);
		}else {
			response=isValid;
		}
		return new ResponseEntity<String>(response, HttpStatus.OK);

	}

	/**
	 * Find OTM,ATM,ITM
	 * 
	 * @param strategy
	 * @return
	 * @throws JSONException
	 * @throws StockAnalyzerException 
	 */
	@PreAuthorize(StockAnalyzerConstant.OAUTH)
	@GetMapping(path = "/admin/findFilterdSymbol", consumes = StockAnalyzerConstant.CONTENTTYPE, produces = StockAnalyzerConstant.CONTENTTYPE)
	public ResponseEntity<?> calOaitmFromWatchListAndInvokeGdfApi() throws JSONException, StockAnalyzerException {
		LOGGER.debug("Entering StockDataAnalyzerController.class calOaitmFromWatchListAndInvokeGdfApi()");
		String response=stockDataAnalyzerService.createoaitmFromWatchList();
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	/**
	 * Find filter symbol
	 * 
	 * @param strategy
	 * @return
	 * @throws StockAnalyzerException 
	 * @throws JSONException
	 */
	@PreAuthorize(StockAnalyzerConstant.OAUTH_ROLE)
	@GetMapping(path = "/user/GetLastQuoteArrayForFilteredSymbol", consumes = StockAnalyzerConstant.CONTENTTYPE, produces = StockAnalyzerConstant.CONTENTTYPE)
	public ResponseEntity<?> getLastQuoteArrayForFilteredSymbol() throws StockAnalyzerException {
		LOGGER.debug("Entering StockDataAnalyzerController.class GetLastQuoteArrayForFilteredSymbol()");
		String response=stockDataAnalyzerService.getLastQuoteArrayForFilteredSymbol();
		return new ResponseEntity<String>(response, HttpStatus.OK);
		
	}


	/**
	 * @param tickData
	 * @return
	 */
	@PreAuthorize(StockAnalyzerConstant.OAUTH)
	@GetMapping(value = "/invokeRest")
	public ResponseEntity<Stock> invokeRest() {
		LOGGER.debug("Entering StockDataAnalyzerController.class invokeRest()");
		// Stock stock = restclient.invokeRest();
		Stock stock = new Stock();
		return new ResponseEntity<Stock>(stock, HttpStatus.OK);
	}

	
}
