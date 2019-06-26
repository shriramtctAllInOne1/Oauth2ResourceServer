/**
 * The ResourceServer Project, BSD License,Copyright (c) 2019
 * All rights reserved.
**/
package com.stock.oauth2.resourceserver.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Model class for one min data
 * 
 * @author shriram
 *
 */

@Document(collection = "StockOneMinData")
public class Stock implements Serializable {

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
	 * strategy low price
	 */
	Double low;
	/**
	 * strategy high price
	 */
	Double high;
	/**
	 * strategy open price
	 */
	Double open;
	/**
	 * strategy close price
	 */
	Double close;
	/**
	 * strategy volume traded
	 */
	Long volume;

	/**
	 * 
	 */
	Long openInterest;

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
	public void setTimeStamp(String timeStamp) {
		this.time = timeStamp;
	}

	/**
	 * @return
	 */
	public Double getLow() {
		return low;
	}

	/**
	 * @param low
	 */
	public void setLow(Double low) {
		this.low = low;
	}

	/**
	 * @return
	 */
	public Double getHigh() {
		return high;
	}

	/**
	 * @param high
	 */
	public void setHigh(Double high) {
		this.high = high;
	}

	/**
	 * @return
	 */
	public Double getOpen() {
		return open;
	}

	/**
	 * @param open
	 */
	public void setOpen(Double open) {
		this.open = open;
	}

	/**
	 * @return
	 */
	public Double getClose() {
		return close;
	}

	/**
	 * @param close
	 */
	public void setClose(Double close) {
		this.close = close;
	}

	/**
	 * @return
	 */
	public Long getVolume() {
		return volume;
	}

	/**
	 * @param volume
	 */
	public void setVolume(Long volume) {
		this.volume = volume;
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

	@Override
	public String toString() {
		return "Stock [id=" + id + ", ticker=" + ticker + ", Date=" + date + ", time=" + time + ", low=" + low
				+ ", high=" + high + ", open=" + open + ", close=" + close + ", volume=" + volume + ", openInterest="
				+ openInterest + "]";
	}

}
