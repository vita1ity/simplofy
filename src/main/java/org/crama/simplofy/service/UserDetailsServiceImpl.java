package org.crama.simplofy.service;

import java.util.Locale;

import org.crama.simplofy.model.Account;
import org.crama.simplofy.model.UserDetailsImpl;
import org.crama.simplofy.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	@Autowired
    private AccountRepository accountRepository;
	
	@Autowired
	private MessageSource messages;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		logger.info("user email: " + email);
		
		Account account = accountRepository.findByEmail(email);
		
		if (account == null) {
			throw new UsernameNotFoundException(messages.getMessage("message.userNotFound", null, Locale.ENGLISH));
		}
        
        return new UserDetailsImpl(account);
	}

	
}
