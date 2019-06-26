/**
 * The ResourceServer Project, BSD License,Copyright (c) 2019
 * All rights reserved.
**/
package com.stock.oauth2.resourceserver.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.result.DeleteResult;
import com.stock.oauth2.resourceserver.constant.StockAnalyzerConstant;
import com.stock.oauth2.resourceserver.dao.StockAnalyzerDAO;
import com.stock.oauth2.resourceserver.domain.Dbcollection;
import com.stock.oauth2.resourceserver.domain.Stock;
import com.stock.oauth2.resourceserver.domain.Strategy;
import com.stock.oauth2.resourceserver.domain.TickerData;
import com.stock.oauth2.resourceserver.dto.OaitmDTO;
import com.stock.oauth2.resourceserver.repository.StockDataAnalyzerRepository;
import com.stock.oauth2.resourceserver.repository.StockTickDataRepository;
import com.stock.oauth2.resourceserver.restClient.RestClient;

/**
 * 
 * Service class for curd operation using mongoTemplate and mongoRepository
 * 
 * @author shriam
 *
 */

@Service
public class StockDataAnalyzerServiceImpl implements StockDataAnalyzerService {

	/**
	 * 
	 */
	@Autowired
	StockDataAnalyzerRepository stockDataAnalyzerRepository;

	/**
	 * 
	 */
	@Autowired
	StockTickDataRepository stockTickDataRepository;

	/**
	 * 
	 */
	@Autowired
	StockAnalyzerDAO stockAnalyzerDAO;

	/**
	 * 
	 */
	@Autowired
	RestClient restclient;

	/**
	 * Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(StockDataAnalyzerServiceImpl.class);

	/**
	 * @param stock
	 */
	@Override
	public Stock saveStockOneMinData(Stock stock) {
		LOGGER.debug("Entering StockDataAnalyzerServiceImpl.class saveStockOneMinData()");
		Stock stockObj = stockDataAnalyzerRepository.save(stock);
		LOGGER.debug("Saved Object {}", stockObj);
		return stockObj;
	}

	/**
	 * @param tickData
	 */
	@Override
	public TickerData saveStockTickData(TickerData tickData) {
		LOGGER.debug("Entering StockDataAnalyzerServiceImpl.class saveStockTickData()");
		TickerData tickDataObj = stockTickDataRepository.save(tickData);
		LOGGER.debug("Saved Object {}", tickDataObj);
		return tickDataObj;
	}

	/**
	 * @param watchListJson
	 * @return
	 */
	@Override
	public String createWatchList(String watchListJson) {
		LOGGER.debug("Entering StockDataAnalyzerServiceImpl.class createStrategy()");
		String strategyObj = stockAnalyzerDAO.createWatchList(watchListJson);
		LOGGER.debug("Saved Obj {}", strategyObj);
		return strategyObj;
	}

	/**
	 * @param stockdto
	 * @return
	 */
	@Override
	public Strategy updateStrategy(Strategy strategy) {
		LOGGER.debug("Entering StockDataAnalyzerServiceImpl.class updateStrategy()");
		Strategy strategObj = stockAnalyzerDAO.updateStrategy(strategy);
		LOGGER.debug("Updated return obj {}", strategObj);
		return strategObj;
	}

	/**
	 * Search strategy
	 * 
	 * @param strategyName
	 * @return
	 */
	@Override
	public Strategy searchStrategy(String strategyName) {
		LOGGER.debug("Entering StockDataAnalyzerServiceImpl.class searchStrategy() searchKey {}", strategyName);
		Strategy strategyObj = stockAnalyzerDAO.searchStrategy(strategyName);
		LOGGER.debug("Searched obj {}", strategyObj);
		return strategyObj;
	}

	/**
	 * @param strategyName
	 * @return
	 */
	@Override
	public DeleteResult deleteStrategy(String strategyName) {
		LOGGER.debug("Entering StockDataAnalyzerServiceImpl.class deleteStrategy() deleteKey {} ", strategyName);
		DeleteResult deleteResult = stockAnalyzerDAO.deleteStrategy(strategyName);
		return deleteResult;
	}

	/**
	 * Method to create Collection in db
	 * 
	 * @param dbcollection
	 * @return
	 */
	@Override
	public void createCollection(Dbcollection dbcollection) {
		LOGGER.debug("Entering StockDataAnalyzerServiceImpl.class createCollection()");
		stockAnalyzerDAO.createCollection(dbcollection);
	}

	@Override
	public Stock findStockOneMinData(String tickerName) {
		LOGGER.debug("Entering StockDataAnalyzerServiceImpl.class findStockOneMinData()");
		return stockAnalyzerDAO.findStockOneMinData(tickerName);
	}

	/**
	 * @param oaitmdto
	 * @return
	 */
	@Override
	public String createAndSaveOAITM(OaitmDTO oaitmdto) {
		LOGGER.debug("Entering StockDataAnalyzerServiceImpl.class createAndSaveOAITM()");
		LinkedHashMap<String, String> finaloaitmMap = generateOAITM(oaitmdto);
		String response = restclient.invokeRest(finaloaitmMap);
		return response;

	}

	/**
	 * @param oaitmdto
	 * @return
	 */
	private LinkedHashMap<String, String> generateOAITM(OaitmDTO oaitmdto) {
		LinkedHashMap<String, String> finaloaitmMap = new LinkedHashMap<String, String>();
		String symbool = oaitmdto.getSymbolName();
		List<String> expiryDate = oaitmdto.getExpiryDate();
		String PE = StockAnalyzerConstant.PE;
		Integer peUp = oaitmdto.getPeUp();
		Integer peDown = oaitmdto.getPeDown();
		Integer futurePrice = oaitmdto.getLtp();
		Integer stepSize = oaitmdto.getStepSize();
		finaloaitmMap
				.putAll(findOAITM(symbool, expiryDate, PE, peDown, StockAnalyzerConstant.DOWN, futurePrice, stepSize));
		finaloaitmMap.putAll(findOAITM(symbool, expiryDate, PE, peUp, StockAnalyzerConstant.UP, futurePrice, stepSize));
		PE = StockAnalyzerConstant.CE;
		Integer ceUp = oaitmdto.getCeUP();
		Integer ceDown = oaitmdto.getCeDown();
		finaloaitmMap
				.putAll(findOAITM(symbool, expiryDate, PE, ceDown, StockAnalyzerConstant.DOWN, futurePrice, stepSize));
		finaloaitmMap.putAll(findOAITM(symbool, expiryDate, PE, ceUp, StockAnalyzerConstant.UP, futurePrice, stepSize));
		return finaloaitmMap;
	}

	/**
	 * @param symboolName
	 * @param expirayDate
	 * @param prefix
	 * @param upDownSize
	 * @param upDownTerm
	 * @param futurePrice
	 * @param stepSize
	 * @return
	 */
	public Map<String, String> findOAITM(String symboolName, List<String> expirayDate, String prefix, int upDownSize,
			String upDownTerm, int futurePrice, int stepSize) {
		LOGGER.debug("Entering StockDataAnalyzerServiceImpl.class findOAITM()");
		LinkedHashMap<String, String> oaitmMap = new LinkedHashMap<String, String>();
		int pivot = findPivot(futurePrice);
		int tempFuturePrice = futurePrice;
		for (int i = 0; i < upDownSize; i++) {
			if (upDownTerm != null && StockAnalyzerConstant.UP.equals(upDownTerm)) {
				tempFuturePrice = tempFuturePrice + 50;
			} else if (upDownTerm != null && StockAnalyzerConstant.DOWN.equals(upDownTerm)) {
				tempFuturePrice = tempFuturePrice - 50;
			}
			String tempoatmValue = checkOAITM(tempFuturePrice, pivot);
			for (String date : expirayDate) {
				StringBuffer sbf = new StringBuffer();
				sbf.append(symboolName).append(date).append(tempFuturePrice).append(prefix);
				oaitmMap.put(sbf.toString(), tempoatmValue);
			}
		}
		return oaitmMap;
	}

	/**
	 * @param cepe
	 * @param pivot
	 * @return
	 */
	public String checkOAITM(int tempFuturePrice, int pivot) {
		LOGGER.debug("Entering StockDataAnalyzerServiceImpl.class checkOAITM()");
		String tempValue = null;
		if (tempFuturePrice < pivot) {
			tempValue = "ITM";
		} else if (tempFuturePrice == pivot) {
			tempValue = "ATM";
		} else if (tempFuturePrice > pivot) {
			tempValue = "OTM";
		}
		return tempValue;

	}

	/**
	 * Find pivot from future price
	 * 
	 * @param futurePrize
	 * @return
	 */
	public int findPivot(int futurePrize) {
		LOGGER.debug("Entering StockDataAnalyzerServiceImpl.class findPivot()");
		int pivot = 0;
		int modules = 0;
		if (futurePrize > 100)
			modules = futurePrize % 100;
		if (modules == 0 || modules == 50) {
			pivot = futurePrize;
		}
		if (modules > 50 && modules < 75) {
			pivot = futurePrize - (modules - 50);
		}
		if (modules > 75) {
			pivot = futurePrize + (100 - modules);
		}
		if (modules < 50 && modules > 25) {
			pivot = futurePrize + (50 - modules);
		}
		if (modules < 25) {
			pivot = futurePrize - modules;
		}
		return pivot;
	}

	/**
	 * @return
	 * @throws IOException 
	 * @throws JSONException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@Override
	public String createoaitmFromWatchList() throws JsonParseException, JsonMappingException, JSONException, IOException {
		LOGGER.debug("Entering StockDataAnalyzerServiceImpl.class createoaitmFromWatchList()");
		List<Document> docList=stockAnalyzerDAO.createoaitmFromWatchList();
		StringBuffer sbf = new StringBuffer();
		docList.parallelStream().filter(doc -> doc.containsKey(StockAnalyzerConstant.WATHCLIST))
			.forEach(doc -> sbf.append((doc.toJson())));
		JSONObject array = new JSONObject(sbf.toString());
		ObjectMapper mapper = new ObjectMapper(); 
		OaitmDTO[] oaitmdtoArray = mapper.readValue(array.getJSONArray(StockAnalyzerConstant.WATHCLIST).toString(), OaitmDTO[].class); 
		LinkedHashMap<String, String> finaloaitmMap = new LinkedHashMap<String, String>();
		for(OaitmDTO oaitmdto : oaitmdtoArray) {
			finaloaitmMap.putAll(generateOAITM(oaitmdto));
		 }
		 String response = restclient.invokeRest(finaloaitmMap);
		 stockAnalyzerDAO.saveFilteredSymbool(response);
		return response;
	}

	/**
	 * @return
	 * @throws IOException 
	 * @throws JSONException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@Override
	public String getLastQuoteArrayForFilteredSymbol() throws JsonParseException, JsonMappingException, JSONException, IOException {
		LOGGER.debug("Entering StockDataAnalyzerServiceImpl.class getLastQuoteArrayForFilteredSymbol()");
		List<Document>docList=stockAnalyzerDAO.getFilteredSymbol();
		StringBuffer sbf = new StringBuffer();
		docList.parallelStream().filter(doc -> doc.containsKey(StockAnalyzerConstant.SYMBOLLIST))
				.forEach(doc -> sbf.append((doc.toJson())));
		JSONObject array = new JSONObject(sbf.toString());
		JSONArray jsonArray= array.getJSONArray(StockAnalyzerConstant.SYMBOLLIST);
		List<String> urlList=new ArrayList<String>();
		 for(Object json : jsonArray) {
			 urlList.add(restclient.createUrl(json.toString()));
		 }
		String response= restclient.invokeGlobalDataFeedAPI(urlList);
		return response;
	}

	}
