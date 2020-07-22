package com.fjl.storemanagment.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.fjl.storemanagment.model.StoreHome;
import com.fjl.storemanagment.service.StoreService;

@Controller
@RequestMapping("/StoreHome")
public class StoreController {
	
	@Autowired
	private StoreService serviceStore;
	
	
	
	@RequestMapping("/stores")
	public String stores(Model model, @RequestParam("pageNo") Optional<Integer> pageNo,
			@RequestParam(value = "pageSize",required = false) Optional<Integer> pageSize ) {
		int page = pageNo.orElse(0);
		int size = pageSize.orElse(10);		
		Pageable paging = PageRequest.of(page, size);
		
		Page<StoreHome> listStore = serviceStore.getAll(paging);
		model.addAttribute("listStore",listStore);		
		return "user/stores";
	}
	
	@GetMapping("/save/{id}")
	public String save (Model model, @PathVariable("id") Integer id) {
		
		
		
		return "save";
		
	}

}
