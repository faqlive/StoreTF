package com.fjl.storemanagment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/Sales")
public class SellController {
	
	
	@GetMapping("/listSale/{idStore}")
	public String save (Model model, @PathVariable("idStore") Integer id) {
	
		
		return "user/pis";
	}

}
