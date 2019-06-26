/**
 * The ResourceServer Project, BSD License,Copyright (c) 2019
 * All rights reserved.
**/

package com.stock.oauth2.resourceserver.config;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Loading property files
 * 
 * @author shriram
 *
 */
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "constant")
public class YAMLConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	String authenticationUrl;
	/**
	 * 
	 */
	String clientId;
	/**
	 * 
	 */
	String clientScret;
	/**
	 * 
	 */
	String resourceId;
	/**
	 * 
	 */
	String urlPattern;

	/**
	 * 
	 */
	String accessPattern;
	
	/**
	 * 
	 */
	String baseUrl;
	
	/**
	 * 
	 */
	String accessKey;
	
	/**
	 * 
	 */
	String apiName;
	/**
	 * 
	 */
	String exchange;

	/**
	 * @return
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * @param clientId
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return
	 */
	public String getClientScret() {
		return clientScret;
	}

	/**
	 * @param clientScret
	 */
	public void setClientScret(String clientScret) {
		this.clientScret = clientScret;
	}

	/**
	 * @return
	 */
	public String getResourceId() {
		return resourceId;
	}

	/**
	 * @param resourceId
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	/**
	 * @return
	 */
	public String getAuthenticationUrl() {
		return authenticationUrl;
	}

	/**
	 * @param authenticationUrl
	 */
	public void setAuthenticationUrl(String authenticationUrl) {
		this.authenticationUrl = authenticationUrl;
	}

	/**
	 * @return
	 */
	public String getUrlPattern() {
		return urlPattern;
	}

	/**
	 * @param urlPattern
	 */
	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern;
	}

	/**
	 * @return
	 */
	public String getAccessPattern() {
		return accessPattern;
	}

	/**
	 * @param accessPattern
	 */
	public void setAccessPattern(String accessPattern) {
		this.accessPattern = accessPattern;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	/**
	 * @return
	 */
	public String getAccessKey() {
		return accessKey;
	}

	/**
	 * @param accessKey
	 */
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	/**
	 * @return
	 */
	public String getApiName() {
		return apiName;
	}

	/**
	 * @param apiName
	 */
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	/**
	 * @return
	 */
	public String getExchange() {
		return exchange;
	}

	/**
	 * @param exchange
	 */
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	@Override
	public String toString() {
		return "YAMLConfig [authenticationUrl=" + authenticationUrl + ", clientId=" + clientId + ", clientScret="
				+ clientScret + ", resourceId=" + resourceId + ", urlPattern=" + urlPattern + ", accessPattern="
				+ accessPattern + "]";
	}
	
	
}
