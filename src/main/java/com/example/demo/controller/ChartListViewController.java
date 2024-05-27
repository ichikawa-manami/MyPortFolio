package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.UrlConst;

@Controller
public class ChartListViewController {

	@GetMapping(UrlConst.CHARTLIST)
	public String listviewdisplay(Model model) {
		
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