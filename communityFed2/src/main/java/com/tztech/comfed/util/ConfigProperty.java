package com.tztech.comfed.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class ConfigProperty {
	
@Value("${email.server}")
private String emailServer; 

@Value("${days.before}")
private int daysBefore;
}
