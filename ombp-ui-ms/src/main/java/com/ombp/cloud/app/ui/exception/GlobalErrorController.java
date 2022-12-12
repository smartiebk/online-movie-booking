package com.ombp.cloud.app.ui.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GlobalErrorController implements ErrorController {

	@Override
	public String getErrorPath() {
		return "/error";
	}


	@RequestMapping("/error")
    public String handleError() {
        //do something like logging
        return "pages/error";
    }

}