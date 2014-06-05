package com.tztech.comfed.util;

import java.util.Locale;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.tztech.comfed.persistence.HibernateDaoImpl;

public interface Validator {
	/**
	 * Validates 
	 * @param dao
	 * @param model
	 * @param result
	 * @param key
	 * @param locale
	 * @param ms
	 * @return
	 */
	public boolean validate(HibernateDaoImpl dao,Model model, BindingResult result, String key, Locale locale, ReloadableResourceBundleMessageSource ms);

}
