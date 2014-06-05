package com.tztech.comfed.form;

import java.util.Locale;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.tztech.comfed.error.CustomError;
import com.tztech.comfed.persistence.HibernateDaoImpl;
import com.tztech.comfed.persistence.entity.CfUser;
import com.tztech.comfed.util.ErrorUtil;

public class SignUpForm extends SignInForm {

	
	
	@NotBlank
	String firstName;
	
	@NotBlank
	String lastName;
	
	@NotBlank
	@Email
	String email;
	
	
	@NotBlank
	String confirmPassword;
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public boolean validate(HibernateDaoImpl dao,Model model, BindingResult result, String key,
			Locale locale,ReloadableResourceBundleMessageSource ms) {
		//check annotation based validation
				if(result.hasErrors()){
					ErrorUtil.processValidationError(result, ms, model, null, locale);
					return false;
				}
				if(!this.getPassword().equals(this.getConfirmPassword())){
					CustomError error = ErrorUtil.getError(ms, "NoMatch.password",null, null, locale);
					model.addAttribute("error",error);
					return false;
				}
//				//check unique user name
				CfUser user = dao.getUserByUserName(this.getUserName());
				if(user != null){
					CustomError error = ErrorUtil.getError(ms, "user.exists",null, this.getUserName(), locale);
					model.addAttribute("error",error);
					return false;
				}
				else return true ;
	}

	
}
