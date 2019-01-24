package org.crama.simplofy.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Account implements Serializable {
	
	public enum UserRole {
		ROLE_USER, ROLE_ADMIN
	}
	
	private static final long serialVersionUID = 3841088944345870785L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	@Transient
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String confirmPassword;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private UserRole role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", email=" + email + ", password=" + password + ", confirmPassword="
				+ confirmPassword + ", role=" + role + "]";
	}
	
}
