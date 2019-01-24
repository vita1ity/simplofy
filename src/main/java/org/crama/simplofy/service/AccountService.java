package org.crama.simplofy.service;

import java.util.List;
import java.util.Locale;

import org.crama.simplofy.error.ObjectNotFoundException;
import org.crama.simplofy.model.Account;
import org.crama.simplofy.model.Story;
import org.crama.simplofy.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    private MessageSource messages;
	
	@Autowired
	private StoryService storyService;
	
	public Account getAccountByEmail(String email) throws ObjectNotFoundException, NoSuchMessageException {
		
		Account account = accountRepository.findByEmail(email);
		
		if (account == null) {
			throw new ObjectNotFoundException("message.userNotFound", messages.getMessage("message.userNotFound", null, Locale.ENGLISH));
		}
		
		return account;
	}

	public Account saveUser(Account user) {
		
		user.setRole(Account.UserRole.ROLE_USER);
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		Account savedUser = accountRepository.save(user);
		
		return savedUser;
		
	}

	public List<Account> findAll() {
		
		return accountRepository.findAll();
	}

	public void delete(Account user) {
		
		List<Story> userStories = storyService.getUserStories(user);
		
		for (Story story: userStories) {
			storyService.delete(story);
		}
		
		accountRepository.delete(user);
		
	}

	public Account getAccountById(Long id) {
		
		Account account = accountRepository.findOne(id);
		
		return account;
	}

	public Account updatePassword(Account oldAccount, String password) {
		oldAccount.setPassword(passwordEncoder.encode(password));
		Account savedUser = accountRepository.save(oldAccount);
		return savedUser;
	}
	

	
}
