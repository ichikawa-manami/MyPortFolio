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
		
		CustomUserDetails userDetails = (CustomUserDetails) loginUser.getPrincipal();
	    
//		ユーザーごとに表示の機能で追記
		 Long userId = userDetails.getId();
	    List<LearningInfo> skillName = learningInfoService.findAll(userId);
	    model.addAttribute("skillName", skillName);
	    
	    
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      
        //Principalからログインユーザの情報を取得
        String userName = auth.getName();
        model.addAttribute("userName", userName);
        
        
        // ログインユーザーの学習データを取得※fb後追記
        List<LearningInfo> learningData = learningInfoService.getLearningData(userId);
        model.addAttribute("learningData", learningData);
		
        
        model.addAttribute("userAddRequest", new UserAddRequest());
        model.addAttribute("email", loginUser.getName());
        model.addAttribute("userName", userDetails.getName());
        model.addAttribute("self_introduction", userDetails.getSelf_introduction());
        
        

        
 
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