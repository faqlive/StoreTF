package com.fjl.storemanagment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fjl.storemanagment.service.StoreService;

@Controller
@RequestMapping("/StoreHome")
public class StoreController {
	
	@Autowired
	private StoreService serviceStore;
	
	
	
	@RequestMapping("/stores")
	public String stores(Model model) {
		model.addAttribute("listStore",serviceStore.getAll());
		return "user/stores";
	}
	
	@GetMapping("/save/{id}")
	public String save (Model model, @PathVariable("id") Integer id) {
		
		
		
		return "save";
		
	}

}
