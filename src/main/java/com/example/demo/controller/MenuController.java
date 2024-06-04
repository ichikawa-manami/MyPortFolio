package com.example.demo.controller;
import java.util.List;

import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.authentication.CustomUserDetails;
import com.example.demo.constant.UrlConst;
import com.example.demo.dto.LearningRequest;
import com.example.demo.dto.UserAddRequest;
import com.example.demo.service.LearningInfoService;
import com.example.demo.entity.LearningInfo;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MenuController {
	
	private final LearningInfoService learningInfoService;
			
	@GetMapping(UrlConst.MENU)
	public String view(Authentication loginUser,Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      
        //Principalからログインユーザの情報を取得
        String userName = auth.getName();
        model.addAttribute("userName", userName);
		
        
        model.addAttribute("userAddRequest", new UserAddRequest());
        model.addAttribute("email", loginUser.getName());
	 CustomUserDetails userDetails = (CustomUserDetails) loginUser.getPrincipal();
        model.addAttribute("userName", userDetails.getName());
        model.addAttribute("self_introduction", userDetails.getSelf_introduction());
        
        
        List<LearningInfo> learningData = learningInfoService.getLearningData();
        model.addAttribute("learningData", learningData);
        
 
		return "menu";
	}
	
	@PostMapping(UrlConst.MENU)
	public String menu(Authentication loginUser, Model model,LearningRequest learningRequest) {
		
		model.addAttribute("userAddRequest", new UserAddRequest());
        model.addAttribute("email", loginUser.getName());
	 CustomUserDetails userDetails = (CustomUserDetails) loginUser.getPrincipal();
        model.addAttribute("userName", userDetails.getName());
        model.addAttribute("self_introduction", userDetails.getSelf_introduction());
		
		return "nemu";
	}
}