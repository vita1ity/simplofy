package org.crama.simplofy.service;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.crama.simplofy.error.ObjectNotFoundException;
import org.crama.simplofy.error.UserNotAuthenticatedException;
import org.crama.simplofy.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private MessageSource messages;

    private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);

    public Account findLoggedInAccount() throws ObjectNotFoundException, NoSuchMessageException, UserNotAuthenticatedException {
    	
    	Authentication authentication = 
				SecurityContextHolder.getContext().getAuthentication();
		if ((authentication == null) || (!authentication.isAuthenticated())) {
		  
			throw new UserNotAuthenticatedException("error.auth.failed", messages.getMessage("error.auth.failed", null, Locale.ENGLISH));
		}
		logger.info("username: " + authentication.getPrincipal());
		
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		Account account = accountService.getAccountByEmail(userDetails.getUsername());
		
		
		/*UserDetails userDetails = (UserDetails)authentication.getPrincipal();
    	
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
        	
        	String email = ((UserDetails)userDetails).getUsername();
        	Account account = accountService.getAccountByEmail(email);
        	
            return account;
        }*/

        return account;
    }

    
    public void authenticateUserAndSetSession(String username, String password, HttpServletRequest request) {
        
        
        logger.info("username: " + username + ", password: " + password);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        // generate session if one doesn't exist
        request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = null;
        try {
        	authenticatedUser = authenticationManager.authenticate(token);
        }
        catch(Exception ex) {
        	logger.info("AuthenticationException: " + ex.getMessage());
        	ex.printStackTrace();
        }
        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }
	
}
