package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.constant.UrlConst;
import com.example.demo.service.LoginService;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

	
	@Autowired
	private UserDetailsService userDetailsService;
	private PasswordEncoder passwordEncoder;
	
	private final String USERNAME_PARAMETER = "email";

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests(authorize -> authorize.requestMatchers(UrlConst.NO_AUTHENTICATION)
				.permitAll()
				.requestMatchers("/css/**")
				.permitAll()
				.anyRequest().authenticated())
		.formLogin(login -> login
				.loginPage(UrlConst.LOGIN)
				.usernameParameter(USERNAME_PARAMETER)
				.passwordParameter("password")
				.defaultSuccessUrl(UrlConst.MENU, true)
				);
		
		return http.build();
	}
	
//	自動ログインで追記
	
	@Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authManagerBuilder = 
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authManagerBuilder
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
        return authManagerBuilder.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}