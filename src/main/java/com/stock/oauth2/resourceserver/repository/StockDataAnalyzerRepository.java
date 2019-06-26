/**
 * The ResourceServer Project, BSD License,Copyright (c) 2019
 * All rights reserved.
**/
package com.stock.oauth2.resourceserver.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stock.oauth2.resourceserver.domain.Stock;

/**
 * MongoRepositroy to handle crud operation for one mind data
 * 
 * @author shriram
 *
 */
public interface StockDataAnalyzerRepository extends MongoRepository<Stock, String> { 
	

    public Stock findByTicker(String ticker);

}
