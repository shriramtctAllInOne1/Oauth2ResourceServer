/**
 * The ResourceServer Project, BSD License,Copyright (c) 2019
 * All rights reserved.
**/
package com.stock.oauth2.resourceserver.restClient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.stock.oauth2.resourceserver.config.ErrorCodeMsgConstant;
import com.stock.oauth2.resourceserver.config.YAMLConfig;
import com.stock.oauth2.resourceserver.exception.StockAnalyzerException;
import com.stock.oauth2.resourceserver.util.CommonUtility;

/**
 * @author shriram
 *
 */

@Service
public class RestClient {

	/**
	 * 
	 */
	@Autowired
	RestTemplate restTemplate;

	/**
	 * 
	 */
	@Autowired
	YAMLConfig config;
	
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
	/**
	 * 
	 */
	private final static int MAX_THREAD = 20;

	/**
	 * Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(RestClient.class);

	/**
	 * @param finaloaitmMap
	 */
	public String invokeRest(LinkedHashMap<String, String> finaloaitmMap)throws StockAnalyzerException  {
		LOGGER.debug("Entering RestClient.class invokeRest()");
		List<String> urlList = createRequest(finaloaitmMap);
		return invokeGlobalDataFeedAPI(urlList);
	}

	/**
	 * @param urlList
	 * @return
	 */
	public String invokeGlobalDataFeedAPI(List<String> urlList) throws StockAnalyzerException {
		LOGGER.debug("Entering RestClient.class invokeGlobalDataFeedAPI()");
		Collection<Callable<String>> tasks = new ArrayList<>();
		StringBuilder sbfResults = new StringBuilder();
		try {
			for (String url : urlList) {
				tasks.add(new Task(url));
			}
			ExecutorService executor = Executors.newFixedThreadPool(MAX_THREAD);
			List<Future<String>> results;
			results = executor.invokeAll(tasks);
			for (Future<String> tempResult : results) {
				sbfResults.append(tempResult.get());
			}
			executor.shutdown();
		} catch (InterruptedException | ExecutionException e) {
			LOGGER.error("Exception invokeGlobalDataFeedAPI() {}",e.getMessage());
			errormsgConfig.setMsg(e.getLocalizedMessage());
			String errorResponse=commonUtility.createErrorResponse(errormsgConfig.getErrrestcode1(), errormsgConfig.getMsg());
			throw new StockAnalyzerException(errorResponse);
		}
		return sbfResults.toString();
	}

	/**
	 * @param symboolList
	 * @return
	 */
	private int findRange(List<String> symboolList) {
		int range;
		if (symboolList.size() % 25 == 0) {
			range = symboolList.size() / 25;
		} else {
			int modules = symboolList.size() % 25;
			range = (symboolList.size() - modules) / 25 + 1;
		}
		return range;
	}

	public String createUrl(String param) {
		String baseUrl = config.getBaseUrl();
		String accessKey = config.getAccessKey();
		String resroucePath=config.getApiName();
		String exchange=config.getExchange();
		StringBuffer sbf = new StringBuffer();
		sbf.append(baseUrl).append("/").append(resroucePath).append("/?").append("accessKey=").append(accessKey);
		sbf.append("&exchange=").append(exchange).append("&isShortIdentifiers=true").append("&instrumentIdentifiers=")
				.append(param);
		return sbf.toString();

	}

	/**
	 * Prepare url's for rest request
	 * 
	 * @param finaloaitmMap
	 * @return
	 */
	private List<String> createRequest(LinkedHashMap<String, String> finaloaitmMap) {
		Set<String> tempSet = finaloaitmMap.keySet();
		List<String> symbolList = new ArrayList<String>(tempSet);
		List<String> urlList = new ArrayList<String>();
		int range;
		range = findRange(symbolList);
		int startIndex = 0;
		int endIndex = 25;
		for (int i = 0; i < range; i++) {
			StringBuffer sbf = new StringBuffer();
			if (endIndex > symbolList.size()) {
				endIndex = startIndex + (symbolList.size() - startIndex);
			}
			List<String> subSymbolList = symbolList.subList(startIndex, endIndex);
			startIndex = endIndex;
			endIndex = startIndex + 25;
			int subListSize = subSymbolList.size();
			for (int index = 0; index < subListSize; index++) {
				sbf.append(subSymbolList.get(index));
				if (index != subListSize - 1) {
					sbf.append("+");
				}
			}
			urlList.add(createUrl(sbf.toString()));
		}
		return urlList;
	}

	private final class Task implements Callable<String> {
		private final String fURL;
		Task(String aURL) {
			fURL = aURL;
		}
		/** Access a URL, and see if you get a healthy response. */
		@Override
		public String call() throws Exception {
			return restTemplate.getForObject(fURL, String.class);
		}

	}
}
