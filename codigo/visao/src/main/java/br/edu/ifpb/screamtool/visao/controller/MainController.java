/**
 * 
 */
package br.edu.ifpb.screamtool.visao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author edsf
 *
 */
@Controller
@RequestMapping("/private/main")
public class MainController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String loadPage(ModelMap model) {
		 
		return "main";
	}

}
