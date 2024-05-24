package com.example.demo.authentication;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CustomUserDetails extends User {

	private String name;
	
	public CustomUserDetails(String email, String password,
			 Collection<? extends GrantedAuthority> authorities, String name) {
		super(email, password, authorities);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}