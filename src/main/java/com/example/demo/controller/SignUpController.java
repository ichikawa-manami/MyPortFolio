package com.example.demo.controller;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.authentication.UserDetailsServiceImpl;
import com.example.demo.constant.MessageConst;
import com.example.demo.constant.UrlConst;
import com.example.demo.dto.UserAddRequest;
import com.example.demo.entity.UserInfo;
import com.example.demo.service.SignupService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SignUpController {
	
    private final SignupService signupService;
	
	private final MessageSource messageSource;
	
	private final AuthenticationManager authenticationManager;
	
	private final UserDetailsServiceImpl userDetailsServiceImpl;
    
    /**
     * ユーザー新規登録画面を表示
     * @param model Model
     * @return ユーザー情報一覧画面
     */
	@GetMapping(UrlConst.SIGNUP)
		public String displayAdd(Model model) {
	        model.addAttribute("userAddRequest", new UserAddRequest());
		return "signup";
	}
	
	/**
     * ユーザー新規登録
     * @param userRequest リクエストデータ
     * @param model Model
     * @return ユーザー情報一覧画面
     */
    @PostMapping(UrlConst.SIGNUP)
    public String create(@Validated @ModelAttribute UserAddRequest userRequest, BindingResult result, 
    		Model model)
   {
        if (result.hasErrors()) {
            // 入力チェックエラーの場合
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
            return "signup";
        }
        
        // ユーザー情報が登録できた場合
        signupService.save(userRequest);
        
        // 自動ログイン
        try {
            UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(userRequest.getEmail());
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(userDetails, userRequest.getPassword(), userDetails.getAuthorities());
            Authentication authentication = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            return "menu";
        }
        return "redirect:/menu"; // トップ画面へ遷移するように変更
    }
   
}
