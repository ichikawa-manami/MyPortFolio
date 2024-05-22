package com.example.demo.controller;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.UrlConst;

@Controller
public class MenuController {

		
	@GetMapping(UrlConst.MENU)
	public String view() {
		return "menu";
	}
	
	@PostMapping(UrlConst.MENU)
	public String menu() {
		return "nemu";
	}
}