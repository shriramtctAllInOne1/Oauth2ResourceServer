/**
 * The ResourceServer Project, BSD License,Copyright (c) 2019
 * All rights reserved.
**/
package com.stock.oauth2.resourceserver.service;

import java.io.IOException;

import org.json.JSONException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mongodb.client.result.DeleteResult;
import com.stock.oauth2.resourceserver.domain.Dbcollection;
import com.stock.oauth2.resourceserver.domain.Stock;
import com.stock.oauth2.resourceserver.domain.Strategy;
import com.stock.oauth2.resourceserver.domain.TickerData;
import com.stock.oauth2.resourceserver.dto.OaitmDTO;
import com.stock.oauth2.resourceserver.exception.StockAnalyzerException;

/**
 * Service layer to interact with DAO Layer
 * 
 * @author shriram
 *
 */
public interface StockDataAnalyzerService {

	/**
	 * @param stock
	 */
	Stock saveStockOneMinData(Stock stock);

	/**
	 * @param tickData
	 */
	TickerData saveStockTickData(TickerData tickData);

	/**
	 * @param watchListJson
	 * @return
	 * @throws StockAnalyzerException 
	 */
	String createWatchList(String watchListJson) throws StockAnalyzerException;

	/**
	 * @param strategy
	 * @return
	 */
	Strategy updateStrategy(Strategy strategy);

	/**
	 * @param strategyName
	 * @return
	 */
	Strategy searchStrategy(String strategyName);

	/**
	 * @param strategyName
	 * @return
	 */
	DeleteResult deleteStrategy(String strategyName);

	/**
	 * @param dbcollection
	 * @throws StockAnalyzerException 
	 */
	void createCollection(Dbcollection dbcollection) throws StockAnalyzerException;
	
	/**
	 * @param tickerName
	 * @return
	 */
	Stock findStockOneMinData(String tickerName);
	
	/**
	 * @param oaitm
	 * @return
	 * @throws StockAnalyzerException 
	 */
	String createOAITMInvokeGlobalDataFeedAPI(OaitmDTO oaitm) throws StockAnalyzerException;
	
	/**
	 * @return
	 * @throws IOException 
	 * @throws JSONException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @throws StockAnalyzerException 
	 */
	String createoaitmFromWatchList() throws StockAnalyzerException;

	
	
	/**
	 * @return
	 * @throws IOException 
	 * @throws JSONException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @throws StockAnalyzerException 
	 */
	String getLastQuoteArrayForFilteredSymbol() throws StockAnalyzerException ;


}
