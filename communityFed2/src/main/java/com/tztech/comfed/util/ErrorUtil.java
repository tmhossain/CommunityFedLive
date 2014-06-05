package com.tztech.comfed.util;

import java.util.List;
import java.util.Locale;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import com.tztech.comfed.error.CustomError;



public class ErrorUtil {
	/**
	 * 
	 * @param messageSource
	 * @param key Key in messages property file
	 * @param defaultMsg Default msg when no key present
	 * @param param Parameter values in msg prop {0}. {1} etc
	 * @param locale
	 * @return Custom error class
	 */
	public static CustomError getError(ReloadableResourceBundleMessageSource messageSource,String key, String defaultMsg,String param,Locale locale){
		CustomError error = new CustomError(101);
		String msg = "";
		if(StringUtil.isNotNull(key)){
			msg = messageSource.getMessage(key,new Object[] { param }, locale);	
		}
		else {
			msg = defaultMsg;
		}
		error.setErrorMessage(msg);
		return error;
	}
	
	/**
	 * Picks the validation error from spring binding result and create a error object, add error object in model
	 * @param result
	 * @param messageSource
	 * @param model
	 * @param id
	 * @param locale
	 */
	public static void processValidationError(BindingResult result, ReloadableResourceBundleMessageSource messageSource,Model model,String id, Locale locale){
		List<FieldError> list = result.getFieldErrors() ;
		for(FieldError e: list){
			String[] p = e.getCodes();
			String key = p[1];
			CustomError error = ErrorUtil.getError(messageSource, key,	"Error", id, locale);
			model.addAttribute("error", error);
			//System.out.println(e.getDefaultMessage());
			//System.out.println("Validation failed");
		}
	}

}
