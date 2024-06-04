package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.UrlConst;
import com.example.demo.dto.DeleteRequest;
import com.example.demo.dto.TimeEditRequest;
import com.example.demo.entity.LearningInfo;
import com.example.demo.service.DeleteService;
import com.example.demo.service.LearningInfoService;
import com.example.demo.service.TimeEditService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChartListViewController {
	
	private final LearningInfoService learningInfoService;
	
	private final TimeEditService timeEditService;
	
	private final DeleteService deleteService;
	
	@GetMapping(UrlConst.CHARTLIST)
	public String listviewdisplay(Model model,String name) {
	    
	    List<LearningInfo> skillName = learningInfoService.findAll();
	    
        model.addAttribute("skillName", skillName);
//        model.addAttribute("studyTime", skillName);
        model.addAttribute("timeEditRequest", new TimeEditRequest());
        
	    
		return "listview";
	}
	
	@PostMapping(UrlConst.CHARTLIST)
	public String listview(Model model,TimeEditRequest timeEditRequest) {
		
//		List<LearningInfo> skillName = learningInfoService.findAll();
//	    
//        model.addAttribute("skillName", skillName);
//        model.addAttribute("studyTime", skillName);
		model.addAttribute("id",timeEditRequest);
      
		timeEditService.timeedit(timeEditRequest);
		
		return "redirect:/listview";
	}
	
	   @PostMapping("/deleteStudyTime")
	    public String deleteStudyTime(Model model, DeleteRequest deleteRequest,
	    		TimeEditRequest timeEditRequest) {
		   
		   model.addAttribute("id",timeEditRequest);
		   deleteService.delete(deleteRequest);
		   
	        return "redirect:/listview";
	    }
}