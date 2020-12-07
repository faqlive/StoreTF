package com.fjl.storemanagment.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fjl.storemanagment.interfaces.ISellService;
import com.fjl.storemanagment.interfaces.IStoreService;
import com.fjl.storemanagment.model.Sell;
import com.fjl.storemanagment.model.StoreHome;
import com.fjl.storemanagment.util.Paginador;


@Controller
@RequestMapping("/Sales")
public class SellController {
	
	@Autowired 
	private ISellService serviceSell;
	
	@Autowired
	private IStoreService serviceStore;
	
	@RequestMapping("/sales_form")
	public String sales(Model model) {

		model.addAttribute("listStore",serviceStore.getAll());
		return "user/sales_form";
	}
	
	@GetMapping("/listSales")
	public String listSales (Model model, @RequestParam("idStore") Integer paramIdStore,
			@RequestParam("dateSince") String strDateSince,
			@RequestParam("dateUntil") String strDateUntil,
			@RequestParam("pageNo") Optional<Integer> pageNo,
			@RequestParam(value = "pageSize",required = false) Optional<Integer> pageSize ) {
				
		Map<Sell, StoreHome> mapSales = new LinkedHashMap<>();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dateSince = (strDateSince == null) ? (LocalDate) model.getAttribute("dateSince")  : LocalDate.parse(strDateSince,format);
		LocalDate dateUntil = (strDateUntil == null) ? (LocalDate) model.getAttribute("dateUntil")  : LocalDate.parse(strDateUntil,format);
		Integer idStore = (paramIdStore == null) ? (Integer) model.getAttribute("idStore") : paramIdStore;
		
		int page = pageNo.orElse(0);
		int size = pageSize.orElse(10);
				
		Pageable paging = PageRequest.of(page, size);
		Page<Sell> pagesSell = (idStore != 0) ? serviceSell.getInStoreAllBetween(dateSince, dateUntil, idStore, paging) 
											: serviceSell.getAllBetween(dateSince, dateUntil, paging) ;		
		
		int totalPage = pagesSell.getTotalPages();
		
		List<Sell> listSell = pagesSell.getContent();
		listSell.forEach(sell -> mapSales.put(sell, serviceStore.get(sell.getSellSotre().getIdStore())));
		
		
		
		model.addAttribute("idStore",idStore);
		model.addAttribute("dateSince",dateSince);
		model.addAttribute("dateUntil",dateUntil);
		model.addAttribute("mapSales", mapSales);
		
		Paginador.paginar(model,totalPage,page);
		
		return "admin/view_sales";
	}

}
