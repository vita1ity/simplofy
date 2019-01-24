package org.crama.simplofy.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.crama.simplofy.model.Account.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 5783685031338056292L;

	private Account account;

	public UserDetailsImpl(Account account) {
		this.account = account;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        UserRole role = account.getRole();
        grantedAuthorities.add(new SimpleGrantedAuthority(role.name()));
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return account.getPassword();
	}

	@Override
	public String getUsername() {
		return account.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

	@Override
	public String toString() {
		return "UserDetailsImpl [account=" + account + "]";
	}

	
	
}
