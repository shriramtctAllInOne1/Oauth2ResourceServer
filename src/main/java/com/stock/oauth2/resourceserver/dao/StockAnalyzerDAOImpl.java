/**
 * The ResourceServer Project, BSD License,Copyright (c) 2019
 * All rights reserved.
**/
package com.stock.oauth2.resourceserver.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.mongodb.client.result.DeleteResult;
import com.stock.oauth2.resourceserver.config.ErrorCodeMsgConstant;
import com.stock.oauth2.resourceserver.domain.Dbcollection;
import com.stock.oauth2.resourceserver.domain.Oaitm;
import com.stock.oauth2.resourceserver.domain.Stock;
import com.stock.oauth2.resourceserver.domain.Strategy;
import com.stock.oauth2.resourceserver.domain.TickerData;
import com.stock.oauth2.resourceserver.exception.StockAnalyzerException;
import com.stock.oauth2.resourceserver.mongoconfig.MongoConfig;
import com.stock.oauth2.resourceserver.util.CommonUtility;

/**
 * DAO layer to perform mongocurd operation using mongoTemplate
 * 
 * @author Shriram
 *
 */
@Component
public class StockAnalyzerDAOImpl implements StockAnalyzerDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(MongoConfig.class);

	/**
	 * 
	 */
	@Autowired
	MongoTemplate mongoTemplate;

	/**
	 * 
	 */
	@Autowired
	CommonUtility commonUtility;

	/**
	 * 
	 */
	@Autowired
	ErrorCodeMsgConstant errormsgConfig;

	@Override
	public void saveStockOneMinData(Stock stock) {
		mongoTemplate.save(stock);
	}

	@Override
	public void saveStockTickData(TickerData tickData) {
		mongoTemplate.save(tickData);
	}

	@Override
	public TickerData findStockTickData(String tickerName) {
		return null;
	}

	/**
	 * Create strategy and save in db
	 * 
	 * @param stockdto
	 * @return
	 */
	@Override
	public String createWatchList(String watchListJson) throws StockAnalyzerException {
		LOGGER.debug("Entering StockAnalyzerDAOImpl.class createStrategy()");
		Document outputDoc = null;
		try {
			Document document = new Document(Document.parse(watchListJson));
			outputDoc = mongoTemplate.save(document, "WatchList");
		} catch (Exception e) {
			String errorResponse = commonUtility.createErrorResponse(errormsgConfig.getErrdaocode5(),
					errormsgConfig.getMsgdao5());
			throw new StockAnalyzerException(errorResponse);
		}
		return outputDoc.toJson();

	}

	/**
	 * Update strategy
	 * 
	 * @param stockdto
	 * @return
	 */
	@Override
	public Strategy updateStrategy(Strategy strategy) {
		return mongoTemplate.save(strategy);
	}

	/**
	 * Search strategy
	 * 
	 * @param strategyName
	 * @return
	 */
	@Override
	public Strategy searchStrategy(String strategyName) {
		Query query = new Query();
		query.addCriteria(Criteria.where("strategyName").in(strategyName));
		return (Strategy) mongoTemplate.find(query, Strategy.class);
	}

	/**
	 * Delete strategy
	 * 
	 * @param strategyName
	 * @return
	 */
	@Override
	public DeleteResult deleteStrategy(String strategyName) {
		Query query = new Query();
		query.addCriteria(Criteria.where("strategyName").in(strategyName));
		return mongoTemplate.remove(query, Strategy.class);
	}

	/**
	 * Create collection
	 * 
	 * @param dbcollection
	 */
	@Override
	public void createCollection(Dbcollection dbcollection) throws StockAnalyzerException {
		try {
			dbcollection.getCollectionNames().forEach((k, v) -> {
				System.out.println("Item : " + k + " Count : " + v);
				if (v != null) {
					mongoTemplate.createCollection(v);
				}
			});
		} catch (Exception e) {
			String errorResponse = commonUtility.createErrorResponse(errormsgConfig.getErrdaocode4(),
					errormsgConfig.getMsgdao4());
			throw new StockAnalyzerException(errorResponse);
		}
	}

	/**
	 * @param tickerName
	 * @return
	 */
	@Override
	public Stock findStockOneMinData(String tickerName) {
		Query searchUserQuery = new Query(Criteria.where("ticker").is(tickerName));
		Stock stock = mongoTemplate.findOne(searchUserQuery, Stock.class);
		return stock;
	}

	/**
	 * @param oaitm
	 */
	@Override
	public void saveOATIMData(Oaitm oaitm) {
		mongoTemplate.save(oaitm);

	}

	/**
	 * @return
	 */
	@Override
	public List<Document> createoaitmFromWatchList() throws StockAnalyzerException {
		LOGGER.debug("Entering StockAnalyzerDAOImpl.class createoaitmFromWatchList()");
		ArrayList<Document> docList = null;
		try {
			docList = mongoTemplate.getCollection("WatchList").find().into(new ArrayList<Document>());
		} catch (Exception e) {
			String errorResponse = commonUtility.createErrorResponse(errormsgConfig.getErrdaocode3(),
					errormsgConfig.getMsgdao3());
			throw new StockAnalyzerException(errorResponse);
		}
		return docList;
	}

	@Override
	public String saveFilteredSymbool(String filteredSymbool) throws StockAnalyzerException {
		LOGGER.debug("Entering StockAnalyzerDAOImpl.class saveFilteredSymbool()");
		Document outputDoc;
		try {
			Document document = new Document(Document.parse(filteredSymbool));
			outputDoc = mongoTemplate.save(document, "SymboolList");
		} catch (Exception e) {
			String errorResponse = commonUtility.createErrorResponse(errormsgConfig.getErrdaocode2(),
					errormsgConfig.getMsgdao2());
			throw new StockAnalyzerException(errorResponse);
		}
		return outputDoc.toJson();

	}

	/**
	 * Fetch saved filtered symbol list
	 * 
	 * @return
	 * @throws StockAnalyzerException
	 */
	@Override
	public List<Document> getFilteredSymbol() throws StockAnalyzerException {
		ArrayList<Document> docList =null;
		try {
			LOGGER.debug("Entering StockAnalyzerDAOImpl.class getFilteredSymbol()");
			docList = mongoTemplate.getCollection("SymboolList").find().into(new ArrayList<Document>());
		} catch (Exception e) {
			String errorResponse = commonUtility.createErrorResponse(errormsgConfig.getErrdaocode1(),
					errormsgConfig.getMsgdao1());
			throw new StockAnalyzerException(errorResponse);
		}
		return docList;
	}

}
