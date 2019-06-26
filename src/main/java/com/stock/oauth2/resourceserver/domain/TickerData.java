/**
 * The ResourceServer Project, BSD License,Copyright (c) 2019
 * All rights reserved.
**/
package com.stock.oauth2.resourceserver.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;

/**
 * Model class for second data ticker data
 * 
 * @author shriram
 *
 */
public class TickerData implements Serializable {

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
	 * Strategy name
	 */
	String ticker;
	/**
	 * Strategy Date
	 */
	Date date;
	/**
	 * Strategy time period
	 */
	String time;
	/**
	 * 
	 */
	Long openInterest;
	/**
	 * last traded price
	 */
	Double ltp;
	/**
	 * last traded quantity
	 */
	Long ltq;
	/**
	 * strategy buy Price
	 */
	Double buyPrice;
	/**
	 * strategy buy quantity
	 */
	Long buyQty;
	/**
	 * strategy sell Price
	 */
	Double sellPrice;
	/**
	 * strategy sell sellQuantity
	 */
	Long sellQty;

	/**
	 * @return
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param timeStamp
	 */
	public void setTime(String timeStamp) {
		this.time = timeStamp;
	}

	/**
	 * @return
	 */
	public Long getOpenInterest() {
		return openInterest;
	}

	/**
	 * @param openInterest
	 */
	public void setOpenInterest(Long openInterest) {
		this.openInterest = openInterest;
	}

	/**
	 * @return
	 */
	public Double getLtp() {
		return ltp;
	}

	/**
	 * @param ltp
	 */
	public void setLtp(Double ltp) {
		this.ltp = ltp;
	}

	/**
	 * @return
	 */
	public Long getLtq() {
		return ltq;
	}

	/**
	 * @param ltq
	 */
	public void setLtq(Long ltq) {
		this.ltq = ltq;
	}

	/**
	 * @return
	 */
	public Double getBuyPrice() {
		return buyPrice;
	}

	/**
	 * @param buyPrice
	 */
	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}

	/**
	 * @return
	 */
	public Long getBuyQty() {
		return buyQty;
	}

	/**
	 * @param buyQuatity
	 */
	public void setBuyQty(Long buyQty) {
		this.buyQty = buyQty;
	}

	/**
	 * @return
	 */
	public Double getSellPrice() {
		return sellPrice;
	}

	/**
	 * @param sellPrice
	 */
	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}

	/**
	 * @return
	 */
	public Long getSellQty() {
		return sellQty;
	}

	/**
	 * @param sellQuantity
	 */
	public void setSellQty(Long sellQuantity) {
		this.sellQty = sellQuantity;
	}

	/**
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return
	 */
	public String getTicker() {
		return ticker;
	}

	/**
	 * @param ticker
	 */
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	
}
