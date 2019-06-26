/**
 * The ResourceServer Project, BSD License,Copyright (c) 2019
 * All rights reserved.
**/
package com.stock.oauth2.resourceserver.domain;

import java.io.Serializable;
import java.util.LinkedHashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "OAITM")
public class Oaitm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Id
	private String id;

	/**
	 * 
	 */
	String symbolName;
	/**
	 * 
	 */
	String expiryDate;
	/**
	 * 
	 */
	Integer peUp;

	/**
	 * 
	 */
	Integer peDown;
	/**
	 * 
	 */
	Integer ceUP;

	/**
	 * 
	 */
	Integer ceDown;
	/**
	 * 
	 */
	Integer stepSize;
	/**
	 * 
	 */
	Integer ltp;

	/**
	 * 
	 */
	String suffix;

	public String getSymbolName() {
		return symbolName;
	}

	public void setSymbolName(String symbolName) {
		this.symbolName = symbolName;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Integer getStepSize() {
		return stepSize;
	}

	public void setStepSize(Integer stepSize) {
		this.stepSize = stepSize;
	}

	public Integer getLtp() {
		return ltp;
	}

	public void setLtp(Integer ltp) {
		this.ltp = ltp;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getPeUp() {
		return peUp;
	}

	public void setPeUp(Integer peUp) {
		this.peUp = peUp;
	}

	public Integer getPeDown() {
		return peDown;
	}

	public void setPeDown(Integer peDown) {
		this.peDown = peDown;
	}

	public Integer getCeUP() {
		return ceUP;
	}

	public void setCeUP(Integer ceUP) {
		this.ceUP = ceUP;
	}

	public Integer getCeDown() {
		return ceDown;
	}

	public void setCeDown(Integer ceDown) {
		this.ceDown = ceDown;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

}
