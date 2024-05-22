package com.example.demo.authentication;

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
				
	return User.builder()
	.username(userInfo.getEmail())
	.password(userInfo.getPassword())
	.build();
	}
}