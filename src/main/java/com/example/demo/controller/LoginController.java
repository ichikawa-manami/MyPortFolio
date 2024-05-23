package com.example.demo.controller;

import java.util.List;

import org.springframework.security.web.WebAttributes;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.MessageConst;
import com.example.demo.constant.UrlConst;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.UserAddRequest;
import com.example.demo.form.LoginForm;
import com.example.demo.dto.LoginRequest;

@Controller
public class LoginController {
	
	@GetMapping(UrlConst.LOGIN)
	public String displayLogin(Model model, LoginForm form) {
		
		return "login";
	}
 
	@PostMapping(UrlConst.LOGIN)
	public String login(Model model, LoginForm form, BindingResult result) {
			
		return "redirect:/menu";
		}
}
