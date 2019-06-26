/**
 * The ResourceServer Project, BSD License,Copyright (c) 2019
 * All rights reserved.
**/
package com.stock.oauth2.resourceserver.dao;

import java.util.List;

import org.bson.Document;

import com.mongodb.client.result.DeleteResult;
import com.stock.oauth2.resourceserver.domain.Dbcollection;
import com.stock.oauth2.resourceserver.domain.Oaitm;
import com.stock.oauth2.resourceserver.domain.Stock;
import com.stock.oauth2.resourceserver.domain.Strategy;
import com.stock.oauth2.resourceserver.domain.TickerData;

/**
 * 
 * DAO Layer to handle db operation
 * 
 * @author Shriram
 *
 */
public interface StockAnalyzerDAO {

	/**
	 * @param stock
	 */
	void saveStockOneMinData(Stock stock);

	/**
	 * @param tickData
	 */
	void saveStockTickData(TickerData tickData);

	/**
	 * @param tickerName
	 * @return
	 */
	Stock findStockOneMinData(String tickerName);
	

	/**
	 * @param tickerName
	 * @return
	 */
	TickerData findStockTickData(String tickerName);
	
	/**
	 * Create strategy and save in db
	 * 
	 * @param stockdto
	 * @return
	 */
	String createWatchList(String watchListJson);

	/**
	 * Update strategy
	 * 
	 * @param stockdto
	 * @return
	 */
	Strategy updateStrategy(Strategy Strategy);

	/**
	 * Search strategy
	 * 
	 * @param strategyName
	 * @return
	 */
	Strategy searchStrategy(String strategyName);

	/**
	 * Delete strategy
	 * 
	 * @param strategyName
	 * @return
	 */
	DeleteResult deleteStrategy(String strategyName);
	
	/**
	 * @param dbcollection
	 */
	void createCollection(Dbcollection dbcollection);
	
	/**
	 * @param oaitm
	 */
	void saveOATIMData(Oaitm oaitm);
	
	
	/**
	 * @return
	 */
	List<Document> createoaitmFromWatchList();
	
	
	/**
	 * @param filteredSymbool
	 */
	String saveFilteredSymbool(String filteredSymbool);

	/**
	 * @return
	 */
	List<Document> getFilteredSymbol();
	
}