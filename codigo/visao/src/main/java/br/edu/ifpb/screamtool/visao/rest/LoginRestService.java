/**
 * 
 */
package br.edu.ifpb.screamtool.visao.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.screamtool.service.negocio.LoginService;
import br.edu.ifpb.screamtool.visao.exception.ResponseEntity;

/**
 * @author edsf
 *
 */
@RestController
@RequestMapping(value = "/service")
public class LoginRestService {

	@Autowired
	@Qualifier("loginService")
	private LoginService loginService;

	@RequestMapping(value = "/login.rest", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Boolean> autenticarUsuario(
			@RequestParam(value = "login", required = true) String login,
			@RequestParam(value = "senha", required = true) String senha) {

		Boolean resultado = new Boolean(loginService.autenticarUsuario(login,
				senha));

		ResponseEntity<Boolean> responseEntity = new ResponseEntity<Boolean>(
				resultado, 200);

		return responseEntity;

	}
}
