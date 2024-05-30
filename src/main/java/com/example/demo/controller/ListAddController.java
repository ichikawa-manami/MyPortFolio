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
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.authentication.CustomUserDetails;
import com.example.demo.constant.UrlConst;
import com.example.demo.dto.LearningRequest;
import com.example.demo.dto.UserAddRequest;
import com.example.demo.entity.CategoryInfo;
import com.example.demo.service.CategoriesService;
import com.example.demo.service.LearningInfoService;
import com.example.demo.service.ListAddService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ListAddController {

	private final ListAddService listAddService;
	private final CategoriesService categoriesService;
	private final LearningInfoService learningInfoService;
	private final UserDetailsService userDetailsService;
	

	@GetMapping(UrlConst.LISTADD)
	public String listadddisplay(@RequestParam("category_id") Integer categoryId, 
			Authentication loginUser, Model model,Long category_id) {

		CustomUserDetails userDetails = (CustomUserDetails) loginUser.getPrincipal();

		model.addAttribute("learningRequest", new LearningRequest());
		model.addAttribute("category_id",categoryId);
		
	    CategoryInfo catName = categoriesService.findByCategoryName(category_id);
        model.addAttribute("name", catName);
		
		
		model.addAttribute("hoge", userDetails.getName());
		model.addAttribute("user_id", userDetails.getId());//Idを取得し、Viewに渡す

		return "listadd";
	}

	@PostMapping(UrlConst.LISTADD)
	public String listadd(@RequestParam("category_id") Integer categoryId, 
			@Validated @ModelAttribute LearningRequest learningRequest,
			BindingResult result, Model model, Authentication authentication, Long category_id,
			String skillName) {
		
		 // 重複チェックをバリデーションエラーとは別に実行
	    if (!result.hasErrors() && learningInfoService.isItemExist(learningRequest.getName())) {
	        result.rejectValue("name", "duplicate", "入力した項目名は既に使用されています");
	    }

		if (result.hasErrors()) {
			// 入力チェックエラーの場合
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}

			model.addAttribute("validationError", errorList);
			model.addAttribute("category_id",categoryId);
			model.addAttribute("userAddRequest", new UserAddRequest());
			
		    CategoryInfo catName = categoriesService.findByCategoryName(category_id);
	        model.addAttribute("name", catName);

			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			model.addAttribute("user_id", userDetails.getId());

			return "listadd";
		}

		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		Long userId = userDetails.getId(); // ユーザーIDを取得

		// 編集内容をアップデート
		listAddService.add(learningRequest);

			 // ユーザー情報を更新してセキュリティコンテキストを再設定
			CustomUserDetails updatedUserDetails = 
				(CustomUserDetails) userDetailsService.loadUserByUsername(authentication.getName());
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
			  updatedUserDetails, authentication.getCredentials(), updatedUserDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authToken);

		return "redirect:/listview";
	}

}
