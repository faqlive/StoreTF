package com.fjl.storemanagment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class InitController {


	
	@RequestMapping("/")
    public String index() {
        return "index";
    }
	
	@RequestMapping("/login")
    public String login() {
        return "login";
    }
	@RequestMapping("/access-denied")
	public String error() {
        return "error/access-denied";
    }
	
	@RequestMapping("/homemenu")
	public String homemenu() {
		return "user/home_menu";
	}
	

	
}
