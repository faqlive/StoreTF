package com.fjl.storemanagment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fjl.storemanagment.service.StoreService;

@Controller
public class InitController {

	@Autowired
	private StoreService serviceStore;	
	
	@RequestMapping("/")
    public String index() {
        return "index";
    }
	
	@RequestMapping("/login")
    public String login() {
        return "login";
    }
	
	@RequestMapping("/homemenu")
	public String homemenu() {
		return "user/home_menu";
	}
	
	@RequestMapping("/sales_form")
	public String sales(Model model) {
		model.addAttribute("listStore",serviceStore.getAll());
		return "user/sales_form";
	}
	
}
