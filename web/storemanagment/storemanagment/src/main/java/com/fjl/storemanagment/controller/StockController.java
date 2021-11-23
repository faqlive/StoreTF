package com.fjl.storemanagment.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fjl.storemanagment.dto.StoreReport;
import com.fjl.storemanagment.model.PisID;
import com.fjl.storemanagment.model.ProductInStore;
import com.fjl.storemanagment.model.StoreHome;
import com.fjl.storemanagment.service.PisService;
import com.fjl.storemanagment.service.ProductService;
import com.fjl.storemanagment.service.StoreService;
import com.fjl.storemanagment.util.Paginador;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
@RequestMapping("/Stock")
public class StockController {
	
	@Autowired
	private PisService servicePis;
	@Autowired
	private ProductService serviceProduct;
	@Autowired
	private StoreService serviceStore;

	@RequestMapping("/form")
	public String stockForm(Model model, @RequestParam("idProduct") Optional<Integer> idProduct,
										@RequestParam("idStore") Optional<Integer> idStore) {
		if(idProduct.isPresent()) {
			model.addAttribute("preIdProduct",idProduct.get());
		}else {
			model.addAttribute("preIdProduct","empty");
		}
		
		if(idProduct.isPresent()) {
			model.addAttribute("preIdStore",idStore.get());
		}else {
			model.addAttribute("preIdStore","empty");
		}
				
		model.addAttribute("listProduct",serviceProduct.getAll());
		model.addAttribute("listStore",serviceStore.getAll());
		return "admin/addStock";
	}
	
	@PostMapping("/add")
	public String addStock(Model model, @RequestParam("idStore") Integer idStore,
									@RequestParam("idProduct") Integer idProduct,
									@RequestParam("quantity") Integer quantity) {
		String msg = "";
		
		if(idStore != 0 && idProduct != 0) {
			ProductInStore pis = new ProductInStore(new PisID(idStore,idProduct),quantity);
			
			msg=servicePis.saveOrUpdate(pis);			
			pis = servicePis.get(new PisID(idStore,idProduct));
			pis.setProduct(serviceProduct.get(idProduct));
			pis.setStore(serviceStore.get(idStore));
			model.addAttribute("pis",pis);
		}else if (idStore == 0 || idProduct == 0) {
			
		}
		//vaciar Formulario
		model.addAttribute("preIdProduct","empty");
		model.addAttribute("preIdStore","empty");
		
		model.addAttribute("listProduct",serviceProduct.getAll());
		model.addAttribute("listStore",serviceStore.getAll());
		model.addAttribute("msg", msg);
		
		return "admin/addStock";
	}
	
	@GetMapping("/checkIn/{idStore}")
	public String checkStoreStock(Model model, @RequestParam(value="pageSize" ,required = false) Optional<Integer> pageSize,
							@RequestParam(value="riskStock" ,required = false) Optional<Integer> riskStock,
									@RequestParam(value="pageNo",required = false) Optional<Integer> pageNo,
									@PathVariable("idStore") Integer idStore) {
		
		
		int page = pageNo.orElse(0);
		int size = pageSize.orElse(10);		
		Pageable paging = PageRequest.of(page, size);
		
		Page<ProductInStore> listPis = servicePis.ceckRiskStockByStore(idStore,riskStock,paging);
		
		int totalPage = listPis.getTotalPages();
		
		Paginador.paginar(model,totalPage,page);
		
		model.addAttribute("listPis", listPis);
		model.addAttribute("idStore", idStore);
		
		return "admin/stock_control";
	}
	
	@GetMapping("/check")
	public String checkRiskStock(Model model,@RequestParam(value="pageNo" ,required = false)Optional<Integer> pageNo,
							@RequestParam(value = "pageSize",required = false) Optional<Integer> pageSize,
							@RequestParam(value="riskStock" ,required = false) Optional<Integer> riskStock) {
		
		int page = pageNo.orElse(0);
		int size = pageSize.orElse(10);	
		Pageable paging = PageRequest.of(page, size);		
		Page<StoreHome> listStore = serviceStore.getAll(paging);
		int totalPage = listStore.getTotalPages();
		
		List<StoreReport> listStoreReport = new ArrayList<StoreReport>();

		listStore.forEach(store -> listStoreReport.add(
				new StoreReport(store, servicePis.countRiskStockByStore(store.getIdStore(), riskStock) ,servicePis.countBreakStockByStore(store.getIdStore()))));
		Paginador.paginar(model,totalPage,page);
		model.addAttribute("listStoreReport",listStoreReport);	
		
		return "admin/store_control";
	}
	
	
}
