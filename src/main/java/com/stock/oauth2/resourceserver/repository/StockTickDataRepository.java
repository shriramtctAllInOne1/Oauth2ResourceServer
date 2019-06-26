/**
 * The ResourceServer Project, BSD License,Copyright (c) 2019
 * All rights reserved.
**/
package com.stock.oauth2.resourceserver.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stock.oauth2.resourceserver.domain.Stock;
import com.stock.oauth2.resourceserver.domain.TickerData;

/**
 * 
 * MongoRepository handle crud operation on tickdata
 * 
 * @author shriram
 *
 */
public interface StockTickDataRepository extends MongoRepository<TickerData, String> {

	public Stock findByTicker(String ticker);
}
