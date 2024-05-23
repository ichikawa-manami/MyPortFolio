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
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.constant.UrlConst;
import com.example.demo.service.LoginService;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
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
	
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        // UserDetailsServiceを設定し、ユーザー認証情報を提供
		 auth.userDetailsService(userDetailsService)
	                // パスワードエンコードを行わない設定
	                .passwordEncoder(NoOpPasswordEncoder.getInstance());
	
}

	 
	@Bean 
	    public AuthenticationManager authenticationManager
	    (AuthenticationConfiguration authenticationConfiguration) throws Exception {
	        return authenticationConfiguration.getAuthenticationManager();
	    }
	 
}