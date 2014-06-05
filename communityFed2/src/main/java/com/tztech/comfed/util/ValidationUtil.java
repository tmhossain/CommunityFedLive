package com.tztech.comfed.util;

import java.util.Locale;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.tztech.comfed.persistence.HibernateDaoImpl;

public class ValidationUtil {

	public static boolean validate(HibernateDaoImpl dao,Validator validator, Model m, BindingResult result, String key, Locale locale,ReloadableResourceBundleMessageSource ms){
		return validator.validate(dao,m, result, key, locale,ms);
		
	}

}
