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
	private Long id;
	private String self_introduction;
	
	public CustomUserDetails(String email, String password,
			 Collection<? extends GrantedAuthority> authorities, String name,
			 Long id, String self_introduction) {
		super(email, password, authorities);
		this.name = name;
		this.id = id;
        this.self_introduction = self_introduction;
	}
	
	public String getName() {
		return name;
}
	
	 public Long getId() {
	    return id;
 }

	 public String getSelf_introduction(){
	    return self_introduction;
 }

}