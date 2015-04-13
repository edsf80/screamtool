/**
 * 
 */
package br.edu.ifpb.screamtool.service.business;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author edsf
 *
 */
public interface LoginService {

	@RequestMapping(value = "/login.rest", method = RequestMethod.POST)
	boolean autenticarUsuario(String login, String senha);
}
