package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.UrlConst;
import com.example.demo.dto.LearningRequest;
import com.example.demo.entity.LearningInfo;
import com.example.demo.repository.UserInfoRepository;
import com.example.demo.service.LearningInfoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChartListViewController {
	
	private final UserInfoRepository userInfoRepository;
	
	private final LearningInfoService learningInfoService;

	@GetMapping(UrlConst.CHARTLIST)
	public String listviewdisplay( Model model) {
	    
	    List<LearningInfo> skillName = learningInfoService.findAll();
	    
        model.addAttribute("skillName", skillName);
        model.addAttribute("studyTime", skillName);
        model.addAttribute("userSearchRequest", new LearningRequest());
        
	    
		return "listview";
	}
	
	@PostMapping(UrlConst.CHARTLIST)
	public String listview(Model model) {
		
		return "listview";
	}
	
	@GetMapping("listadd")
	public String listaddview() {
		return "listadd";
	}
}