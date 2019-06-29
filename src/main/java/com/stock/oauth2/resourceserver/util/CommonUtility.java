package com.stock.oauth2.resourceserver.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.stock.oauth2.resourceserver.config.ErrorCodeMsgConstant;
import com.stock.oauth2.resourceserver.constant.StockAnalyzerConstant;

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

	@Autowired
	ResourceLoader resourceLoader;
	
	/**
	 * 
	 */
	@Autowired
	ErrorCodeMsgConstant errormsgConfig;

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
		if (inputInteger < 0 && inputInteger == 0)
			return true;
		return false;
	}

	/**
	 * @param validatorObj
	 * @param pojo
	 * @param pojoName
	 * @return
	 */
	public String checkRequest(Validator validatorObj, Object pojo, String pojoName) {
		BeanPropertyBindingResult result = new BeanPropertyBindingResult(pojo, pojoName);
		ValidationUtils.invokeValidator(validatorObj, pojo, result);
		String validatonResult=StockAnalyzerConstant.ISTURE;
		if (result.hasErrors()) {
			validatonResult=createErrorResponse(errormsgConfig.getErrcode101(),errormsgConfig.getMsg101());
		} 
		return validatonResult;
	}

	/**
	 * @param jsonSchema
	 * @param jsonRequest
	 */
	public String validateJson(String jsonSchema, String jsonRequest) {
		String response =StockAnalyzerConstant.ISTURE;
		try (InputStream inputStream = resourceLoader.getResource("classPath:schema/" + jsonSchema).getInputStream()) {
			JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
			Schema schema = SchemaLoader.load(rawSchema);
			schema.validate(new JSONObject(jsonRequest));
		} catch (JSONException | ValidationException | IOException e) {
			LOGGER.error("Entering CommonUtility.class validateJson()");
			errormsgConfig.setMsg101(e.getLocalizedMessage());
			response = createErrorResponse(errormsgConfig.getErrcode102(), errormsgConfig.getMsg101());
		}
		return response;
	}

	/**
	 * @param errorCode
	 * @param errorMsg
	 * @return
	 */
	public String createErrorResponse(String errorCode, String errorMsg) {
		LOGGER.debug("Entering CommonUtility.class createErrorResponse()");
		JSONObject responseObj = new JSONObject();
		responseObj.put("Code", errorCode);
		responseObj.put("ErrorMsg", errorMsg);
		return responseObj.toString();

	}

	/**
	 * @param errorCode
	 * @param errorMsg
	 * @return
	 */
	public String createSucessResponse(String code, String msg) {
		LOGGER.debug("Entering CommonUtility.class createSucessResponse()");
		JSONObject responseObj = new JSONObject();
		responseObj.put("Code", code);
		responseObj.put("Msg", msg);
		return responseObj.toString();
	}

}
