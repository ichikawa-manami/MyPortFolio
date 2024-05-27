package com.example.demo.authentication;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	

	private final UserInfoRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserInfo userInfo = repository.findByEmail(email)
				.orElseThrow(()-> new UsernameNotFoundException
						("User not found with email: " + email));
		
		Collection<? extends GrantedAuthority> authorities =
		Collections.singletonList(new SimpleGrantedAuthority("USER"));
	    return new CustomUserDetails
	    		(userInfo.getEmail(), userInfo.getPassword(), authorities,
	    				userInfo.getName(),userInfo.getId(), userInfo.getSelf_introduction());
				
	 /**
	return User.builder()/
	.username(userInfo.getEmail())
	.password(userInfo.getPassword())
	.username(userInfo.getName())
    .roles("USER")
	.build();
	}
	**/
	
}
}