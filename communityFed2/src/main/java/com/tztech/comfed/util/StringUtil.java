package com.tztech.comfed.util;

public class StringUtil {

	/**
	 * Checks for null and empty string
	 * @param input
	 * @return
	 */
	public static boolean isNotNull(String input){
		boolean result = false;
		if(input != null & input.length() > 0){
			result = true;
		}
		return result;
	}
}
