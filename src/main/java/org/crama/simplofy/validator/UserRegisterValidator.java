package org.crama.simplofy.validator;

import org.crama.simplofy.error.ObjectNotFoundException;
import org.crama.simplofy.model.Account;
import org.crama.simplofy.service.AccountService;
import org.crama.simplofy.utils.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserRegisterValidator implements Validator {

	private AccountService accountService;
	
	//private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Override
    public boolean supports(Class<?> clazz) {
        return Account.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors e) {

        ValidationUtils.rejectIfEmpty(e, "email", "error.email.empty");
        ValidationUtils.rejectIfEmpty(e, "password", "error.password.empty");
        Account user = (Account) target;

        int emailLength = user.getEmail().length();
        if (emailLength < 5 || emailLength > 40) {
        	e.rejectValue("email", "error.email.invalidLength");
        }
        
        if (user.getPassword().length() < 6 || user.getPassword().length() > 50) {
        	e.rejectValue("password", "error.password.invalidLength");
        }
        
        EmailValidator emailValidator = new EmailValidator();
	    if (!emailValidator.validate(user.getEmail())) {
	    	e.rejectValue("email", "error.email.notValid");
	    }
	    
		try {
			accountService.getAccountByEmail(user.getEmail());
			e.rejectValue("email", "error.email.alreadyExist");
		} catch (ObjectNotFoundException ex) {
			//user not found - do nothing
		}
		
        
        if (!user.getPassword().equals(user.getConfirmPassword())) {
        	e.rejectValue("confirmPassword" , "error.confirmPassword.notEqual");
        }
        
    }

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

    
    
	
}
