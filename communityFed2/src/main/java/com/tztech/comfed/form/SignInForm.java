package com.tztech.comfed.form;

import java.util.Locale;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.tztech.comfed.error.CustomError;
import com.tztech.comfed.persistence.HibernateDaoImpl;
import com.tztech.comfed.persistence.entity.CfUser;
import com.tztech.comfed.util.ErrorUtil;
import com.tztech.comfed.util.Validator;

public class SignInForm implements Validator {

	//auto gen primary key in db
	Long id;
	

	//user logs on with
	@NotBlank
	@Size(min=4)
	String userName;
	
	@NotBlank
	@Size(min=8)
	String password;
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean validate(HibernateDaoImpl dao,Model model, BindingResult result, String key,
			Locale locale,ReloadableResourceBundleMessageSource ms) {
		
		//check annotation based validation
		if(result.hasErrors()){
			ErrorUtil.processValidationError(result, ms, model, null, locale);
			return false;
		}
		//check valid user
		CfUser user = dao.getUserByUserName(this.getUserName());
		if(user == null) {
			CustomError error = ErrorUtil.getError(ms, "Invalid.Logon",null, this.getUserName(), locale);
			model.addAttribute("error",error);
			return false;
		}
		//check password here
		else if (!user.getPassword().equals(this.getPassword())) {
			CustomError error = ErrorUtil.getError(ms, "Invalid.Logon",null, this.getUserName(), locale);
			return false;
		}
		else return true;
	}
	
	

	
	
}
