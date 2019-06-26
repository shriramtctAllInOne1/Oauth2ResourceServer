/**
 * The ResourceServer Project, BSD License,Copyright (c) 2019
 * All rights reserved.
**/
package com.stock.oauth2.resourceserver.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.stock.oauth2.resourceserver.dto.OaitmDTO;

/**
 * 
 * Strategy model calss
 * 
 * @author Shriram
 *
 */
@Document(collection = "WatchList")
public class Strategy implements Serializable {

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
	OaitmDTO oaitmdto;

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
	public OaitmDTO getOaitmdto() {
		return oaitmdto;
	}

	/**
	 * @param oaitmdto
	 */
	public void setOaitmdto(OaitmDTO oaitmdto) {
		this.oaitmdto = oaitmdto;
	}
	
	
}
