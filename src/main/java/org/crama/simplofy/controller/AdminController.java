package org.crama.simplofy.controller;

import java.util.List;
import java.util.Locale;

import org.crama.simplofy.error.NoIdException;
import org.crama.simplofy.error.ObjectNotFoundException;
import org.crama.simplofy.model.Account;
import org.crama.simplofy.service.AccountService;
import org.crama.simplofy.service.PageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class AdminController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private MessageSource messages;
	
	private static final Logger logger = LoggerFactory.getLogger(PageService.class);
	
	@RequestMapping(value="/admin", method=RequestMethod.GET)
    public String showAdminPage(Model model) {
		
		List<Account> allUsers = accountService.findAll();
		
		logger.info("users: " + allUsers);
		
		model.addAttribute("allUsers", allUsers);
		
        return "admin";
    }

	@RequestMapping(value="/admin/edit", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
    public @ResponseBody Account editAccount(@RequestBody Account user) throws NoIdException, NoSuchMessageException, ObjectNotFoundException {
		
		if (user.getId() == null) {
			throw new NoIdException("error.noid", messages.getMessage("error.noid", null, Locale.ENGLISH));
		}
		if (user.getPassword() == null || user.getPassword().equals("")) {
			throw new ObjectNotFoundException("error.password.empty", messages.getMessage("error.password.empty", null, Locale.ENGLISH));
		}
		Account oldAccount = accountService.getAccountById(user.getId());
		Account editedAccount = accountService.updatePassword(oldAccount, user.getPassword());
		
		return editedAccount;
    }
	
	@RequestMapping(value="/admin/delete", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String deleteAccount(@RequestBody Account user) {
		
		accountService.delete(user);
		
        return "success";
    }
	
}
