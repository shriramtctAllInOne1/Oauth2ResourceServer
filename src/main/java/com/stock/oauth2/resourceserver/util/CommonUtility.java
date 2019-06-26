package com.stock.oauth2.resourceserver.util;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ValidationUtils;

/**
 * @author shriram
 *
 */

@Service
public class CommonUtility {

	/**
	 * Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtility.class);

	/**
	 * @param inputString
	 * @return
	 */
	public boolean isNullOrEmpty(String inputString) {
		LOGGER.debug("Entering CommonUtility.class isNullOrEmpty() inputString : {}", inputString);
		if (inputString != null && !inputString.trim().isEmpty())
			return false;
		return true;
	}

	/**
	 * @param list
	 * @return
	 */
	public boolean inNullorEmptyList(List<String> list) {
		LOGGER.debug("Entering CommonUtility.class inNullorEmptyList()");
		if (CollectionUtils.isEmpty(list))
			return true;
		return false;
	}
	
	public boolean isValidNumber(Integer inputInteger) {
		LOGGER.debug("Entering CommonUtility.class isValidNumber()");
		if(inputInteger<0 && inputInteger==0 )
			return true;
	  return false;
	}
	
	public void inovkeValidator(Object obj,String pojoName,Object reference,String className) {
		
	}
}
