package com.tztech.comfed;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tztech.comfed.error.CustomError;
import com.tztech.comfed.form.SignInForm;
import com.tztech.comfed.form.SignUpForm;
import com.tztech.comfed.persistence.HibernateDaoImpl;
import com.tztech.comfed.persistence.entity.CfUser;
import com.tztech.comfed.util.ConfigProperty;
import com.tztech.comfed.util.ErrorUtil;
import com.tztech.comfed.util.ValidationUtil;
//import org.hsqldb.rights.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	
	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);
	@Autowired
	HibernateDaoImpl dao;
	@Autowired
	ReloadableResourceBundleMessageSource messageSource;
	@Autowired
	ConfigProperty configProperty;
	

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		// model.addAttribute("vio", vio );
		return "home";
	}

	@RequestMapping(value = "/ajaxtest", method = RequestMethod.GET)
	public String ajaxtest(Locale locale, Model model) {
		System.out.println("in server method ajaxtest");
		return "ajaxtest";
	}
	
	@RequestMapping(value = "/frmInput", method = RequestMethod.GET)
	public String frmInput(Locale locale, Model model) {
		System.out.println("in server method frmInput");
		SignUpForm userForm = new SignUpForm();
		model.addAttribute("userForm", userForm);
		return "frmInput";
	}
		
	//rest client url http://localhost:8080/tolling/restToJson?id=52
	//returns {"id":"52","accountId":101}
	
	@RequestMapping(value = "/restGet", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public SignUpForm restGet(@RequestParam("id") String id, Model model){
		//model.setcon
		SignUpForm form = new SignUpForm();
		form.setId(new Long(id));
		form.setFirstName("Vali");
		form.setLastName("Sheikh");
		return form;
	}
	
	@RequestMapping(value = "/fSubmit", method = RequestMethod.POST )
	public String fSubmit(@Valid SignUpForm userForm, BindingResult result,
			Model model, Locale locale){
		if(result.hasErrors()){
			return "frmInput";
		}
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
		DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		
		System.out.println("In method /fSubmit");
		//System.out.println(userForm.getUserId());
		System.out.println(userForm.getFirstName());
		System.out.println(userForm.getLastName());
		model.addAttribute("frm",userForm);
		return "frmResult";
	}
	@RequestMapping(value = "/cfHome", method = RequestMethod.GET)
	public String cfHome(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		// model.addAttribute("vio", vio );
		return "cfHome";
	}
	@RequestMapping(value = "/comFed", method = RequestMethod.GET)
	public String comFed(Locale locale, Model model) {
		
		return "comfed/index";
		
	}
	
	@RequestMapping(value = "/hbTest", method = RequestMethod.GET)
	public String hbTest(Locale locale, Model model) {
		
		return "comfed/hbTest";
		
	}
	
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public String getUser(@RequestParam("id") Long id,Locale locale, Model model) {
		CfUser user = null ;
		user = (CfUser)dao.load(CfUser.class,id);
		
		model.addAttribute("cfuser",user);
		return "comfed/hbResult";
		
	}
	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	public String signUp(Locale locale, Model model) {
		SignUpForm user = new SignUpForm();
		model.addAttribute("user",user);
		return "comfed/signUp";
		
	}
	
	@RequestMapping(value = "/signupSubmit", method = RequestMethod.POST )
	public String signupSubmit(@Valid SignUpForm user, BindingResult result,
			Model model, Locale locale){
		boolean success =ValidationUtil.validate(dao, user, model, result, "",locale, messageSource);
		if(!success){
			model.addAttribute("user",user);
			//validation fail path
			return "comfed/signUp";
		}
		else {
			try {
				Long id = dao.addUser(user);
				CfUser cfUser = (CfUser)dao.load(CfUser.class,id );
				model.addAttribute("user",cfUser);
				//success path
				return "comfed/userHome";
			} catch (Exception e) {
				CustomError error = ErrorUtil.getError(messageSource, "", e.getMessage(), null, locale);
				model.addAttribute("error", error);
			}
			
		}
		//exception path
		model.addAttribute("user",user);
		return "comfed/signUp";
	}
	
	@RequestMapping(value = "/signIn", method = RequestMethod.GET)
	public String signIn(Locale locale, Model model) {
		SignInForm user = new SignInForm();
		//add dummy data to non required field
		//user.setConfirmPassword("");
		//user.setEmail("a@bc.com");
		model.addAttribute("user",user);
		return "comfed/signIn";
		
	}
	
	@RequestMapping(value = "/signInSubmit", method = RequestMethod.POST )
	public String signInSubmit(@Valid SignInForm user, BindingResult result,
			Model model, Locale locale){
		//boolean success = ValidationUtil.validate(dao,result, user, messageSource, model, locale);
		boolean success =ValidationUtil.validate(dao, user, model, result, "",locale, messageSource);
		if(!success){
			model.addAttribute("user",user);
			return "comfed/signIn";
		}
		CfUser validUser = dao.getUserByUserName(user.getUserName());
		model.addAttribute("user",validUser);
		return "comfed/userHome";
	}
	
	@RequestMapping(value = "/aboutUs", method = RequestMethod.GET)
	public String aboutUs(Locale locale, Model model) {
		SignUpForm user = new SignUpForm();
		model.addAttribute("user",user);
		return "comfed/aboutUs";
		
	}
	@RequestMapping(value = "/howItWorks", method = RequestMethod.GET)
	public String howItWorks(Locale locale, Model model) {
		SignUpForm user = new SignUpForm();
		model.addAttribute("user",user);
		return "comfed/howItWorks";
		
	}
}
