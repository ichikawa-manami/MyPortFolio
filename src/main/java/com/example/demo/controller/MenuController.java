package com.example.demo.controller;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.UrlConst;

@Controller
public class MenuController {

		
	@GetMapping(UrlConst.MENU)
	public String view(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      
        //Principalからログインユーザの情報を取得
        String userName = auth.getName();
        model.addAttribute("userName", userName);
		
		return "menu";
	}
	
	@PostMapping(UrlConst.MENU)
	public String menu(Model model) {
		return "nemu";
	}
}