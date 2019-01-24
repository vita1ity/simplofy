package org.crama.simplofy.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.crama.simplofy.model.Account;
import org.crama.simplofy.service.AccountService;
import org.crama.simplofy.service.SecurityService;
import org.crama.simplofy.validator.UserRegisterValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AccountController {

	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private MessageSource messageSource;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    UserRegisterValidator validator = new UserRegisterValidator();
	    validator.setAccountService(accountService);
		binder.setValidator(validator);
		
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
    public String showRegistrationPage(Model model) {
		if (!model.containsAttribute("signupForm")) {
	        model.addAttribute("signupForm", new Account());
	    }
        return "registration";
    }
	
	@RequestMapping(value="/registration", method=RequestMethod.POST)
    public String register(@ModelAttribute("signupForm") @Valid Account signupForm, BindingResult result, 
    		RedirectAttributes redirectAttributes, HttpServletRequest request) {
		
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.signupForm", result);
			redirectAttributes.addFlashAttribute("signupForm", signupForm);
			return "redirect:/";
		}
		
		String password = signupForm.getPassword();
		
		logger.info("registration method: " + signupForm.getEmail() + ", " + password);
		
		Account registered = accountService.saveUser(signupForm);
		
		
		//securityService.autologin(signupForm.getEmail(), signupForm.getPassword());
		securityService.authenticateUserAndSetSession(signupForm.getEmail(), password, request);
		
		redirectAttributes.addFlashAttribute("message", messageSource.getMessage("message.user.registerSuccess", null, Locale.ENGLISH));
		
		return "redirect:/stories";
        
    }
	
	
}
