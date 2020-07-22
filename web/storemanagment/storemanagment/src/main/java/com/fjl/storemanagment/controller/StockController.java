package com.fjl.storemanagment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fjl.storemanagment.model.PisID;
import com.fjl.storemanagment.model.ProductInStore;
import com.fjl.storemanagment.service.PisService;
import com.fjl.storemanagment.service.ProductService;
import com.fjl.storemanagment.service.StoreService;

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
	public String stockForm(Model model) {
				
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

		model.addAttribute("listProduct",serviceProduct.getAll());
		model.addAttribute("listStore",serviceStore.getAll());
		model.addAttribute("msg", msg);
		
		return "admin/addStock";
	}
	
}
