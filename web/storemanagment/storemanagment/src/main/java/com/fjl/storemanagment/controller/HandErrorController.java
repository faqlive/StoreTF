package com.fjl.storemanagment.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HandErrorController implements ErrorController {
	
	@RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {

	        String errorMsg = "";
	        int httpErrorCode = getErrorCode(request);
	 
	        switch (httpErrorCode) {
	            case 400: {
	                errorMsg = "Http Error Code: 400. Bad Request";
	                break;
	            }
	            case 401: {
	                errorMsg = "Http Error Code: 401. Unauthorized";
	                break;
	            }
	            case 404: {
	                errorMsg = "Http Error Code: 404. Resource not found";
	                break;
	            }
	            case 500: {
	                errorMsg = "Http Error Code: 500. Internal Server Error";
	                break;
	            }
	        }
	        model.addAttribute("errorMsg", errorMsg);
		
        return "/error/error";
    }
 
    @Override
    public String getErrorPath() {
        return "/error/error";
    }
    
    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
          .getAttribute("javax.servlet.error.status_code");
    }

}
