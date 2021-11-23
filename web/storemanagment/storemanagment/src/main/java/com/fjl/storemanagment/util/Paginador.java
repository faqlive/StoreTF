package com.fjl.storemanagment.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.ui.Model;

public class Paginador {

	public static void paginar(Model model, int totalPage, int page) {
		// SE PUEDE REFACTORIZAR
				model.addAttribute("curretPage", page);
				model.addAttribute("lastPage", totalPage);
				if (totalPage > 0) {
					List<Integer> listPage = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
					model.addAttribute("listPage",listPage);
				}
				
				int firstValue=0;
				int lastValue=0;
				if(page < 4) {
					firstValue = 0;
					lastValue = 5;
				}else if (page > (totalPage - 5)){
					firstValue = totalPage - 5;
					lastValue = totalPage;
				}else {
					firstValue = page - 2;
					lastValue = page + 3;
				}
				model.addAttribute("firstValue", firstValue);
				model.addAttribute("lastValue", lastValue);
		//FIN DE REFACTORIZAR

	}
}
