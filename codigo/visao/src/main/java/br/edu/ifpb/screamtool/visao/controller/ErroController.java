/**
 * 
 */
package br.edu.ifpb.screamtool.visao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author edsf
 *
 */
@Controller
@RequestMapping("/private/erro")
public class ErroController {

	@RequestMapping(method = RequestMethod.GET)
	public String loadPage() {
		return "erro";
	}
}
