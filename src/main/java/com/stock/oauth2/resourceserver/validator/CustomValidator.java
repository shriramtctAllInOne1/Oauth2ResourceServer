package com.stock.oauth2.resourceserver.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.stock.oauth2.resourceserver.dto.OaitmDTO;
import com.stock.oauth2.resourceserver.util.CommonUtility;


@Component
public class CustomValidator implements Validator   {
	
	/**
	 * 
	 */
	@Autowired
	CommonUtility commonUtility;
	
	/**
	 * @param clazz
	 * @return
	 */
	@Override
    public boolean supports(Class<?> clazz) {
        return OaitmDTO.class.equals(clazz);
    }
 
    @Override
    public void validate(Object obj, Errors errors) {
    	OaitmDTO oaitm = (OaitmDTO) obj;
    	if(commonUtility.isNullOrEmpty(oaitm.getSymbolName())) {
    		errors.rejectValue("symbolName", "symbolName Required");
    	}
    	if(commonUtility.inNullorEmptyList(oaitm.getExpiryDate())) {
    		errors.rejectValue("expiryDate", "expiryDate Required");
    	}
    	if(commonUtility.isValidNumber(oaitm.getCeDown())) {
    		errors.rejectValue("ceDown", "CE-DOWN Required");
    	}
    	if(commonUtility.isValidNumber(oaitm.getCeUP())) {
    		errors.rejectValue("ceUP", "CE-UP Required");
    	}

    	if(commonUtility.isValidNumber(oaitm.getPeUp())) {
    		errors.rejectValue("peUp", "PE-UP Required");
    	}
    	if(commonUtility.isValidNumber(oaitm.getPeDown())) {
    		errors.rejectValue("peDown", "PE-DOWN Required");
    	}
    	if(commonUtility.isValidNumber(oaitm.getStepSize())) {
    		errors.rejectValue("stepSize", "Stepsize Required");
    	}
    	if(commonUtility.isValidNumber(oaitm.getLtp())) {
    		errors.rejectValue("ltp", "LastTraded Price Required");
    	}


    }
 
   

}
