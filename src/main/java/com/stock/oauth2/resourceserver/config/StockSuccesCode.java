package com.stock.oauth2.resourceserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@EnableConfigurationProperties
@PropertySource(value = "classpath:application-errorcodedao.yml")
@ConfigurationProperties(prefix = "succcode")
public class StockSuccesCode {

	/**
	 * 
	 */
	@Value("${succmsg}")
	String succmsg;

	/**
	 * 
	 */
	@Value("${succcode1}")
	public String succcode1;
	/**
	 * 
	 */
	@Value("${succcode2}")
	public String succcode2;
	/**
	 * 
	 */
	@Value("${succcode3}")
	public String succcode3;

	public String getSuccmsg() {
		return succmsg;
	}

	public void setSuccmsg(String succmsg) {
		this.succmsg = succmsg;
	}

	public String getSucccode1() {
		return succcode1;
	}

	public void setSucccode1(String succcode1) {
		this.succcode1 = succcode1;
	}

	public String getSucccode2() {
		return succcode2;
	}

	public void setSucccode2(String succcode2) {
		this.succcode2 = succcode2;
	}

	public String getSucccode3() {
		return succcode3;
	}

	public void setSucccode3(String succcode3) {
		this.succcode3 = succcode3;
	}

}
