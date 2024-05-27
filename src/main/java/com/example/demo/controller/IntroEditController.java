package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.authentication.CustomUserDetails;
import com.example.demo.constant.UrlConst;
import com.example.demo.dto.IntroEditRequest;
import com.example.demo.service.IntroEditService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class IntroEditController {
	
	 private final IntroEditService introEditService;
	 private final UserDetailsService userDetailsService;

	@GetMapping(UrlConst.S_I_EDIT)
	public String introeditview(Authentication loginUser, Model model) {
		
        CustomUserDetails userDetails = (CustomUserDetails) loginUser.getPrincipal();

		 model.addAttribute("introEditRequest", new IntroEditRequest());
         model.addAttribute("hoge", userDetails.getName());
         model.addAttribute("id",userDetails.getId());//Idを取得し、Viewに渡す
         
	return "introedit";
}
	
	@PostMapping(UrlConst.S_I_EDIT)
	public String introedit(@Validated @ModelAttribute IntroEditRequest introRequest, 
			BindingResult result, 
    		Model model, Authentication authentication) {
		
		if (result.hasErrors()) {
            // 入力チェックエラーの場合
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
            
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            model.addAttribute("id", userDetails.getId());
            return "introedit";
        }
		
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
	    Long userId = userDetails.getId(); // ユーザーIDを取得
	    
	    // 編集内容をアップデート
		introEditService.edit(introRequest);

	    // ユーザー情報を更新してセキュリティコンテキストを再設定
	    CustomUserDetails updatedUserDetails = 
	    	(CustomUserDetails) userDetailsService.loadUserByUsername(authentication.getName());
	    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
	        updatedUserDetails, authentication.getCredentials(), updatedUserDetails.getAuthorities());
	    SecurityContextHolder.getContext().setAuthentication(authToken);

		return "redirect:/menu";
	}
}
