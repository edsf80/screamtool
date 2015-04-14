/**
 * 
 */
package br.edu.ifpb.screamtool.service.business;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author edsf
 *
 */
public interface LoginService {

	@RequestMapping(value = "/login.rest", method = RequestMethod.POST)
	@ResponseBody boolean autenticarUsuario(
			@RequestParam(value = "login", required = true) String login,
			@RequestParam(value = "senha", required = true) String senha);
}
