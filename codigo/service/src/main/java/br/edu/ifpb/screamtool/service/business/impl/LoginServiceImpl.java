/**
 * 
 */
package br.edu.ifpb.screamtool.service.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.screamtool.service.business.LoginService;

/**
 * @author edsf
 *
 */
@RestController
@RequestMapping(value = "/service")
public class LoginServiceImpl implements LoginService {

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.edu.ifpb.screamtool.service.business.LoginService#autenticarUsuario
	 * (java.lang.String, java.lang.String)
	 */
	public boolean autenticarUsuario(String login, String senha) {
		if (login == null || senha == null) {
			throw new IllegalArgumentException(
					"Tentativa de autenticacao de login ou senha nula");
		}

		Authentication authentication = this.criarAutenticacao(login, senha);

		if (authentication == null) {
			return Boolean.FALSE;
		}

		SecurityContextHolder.getContext().setAuthentication(authentication);

		return Boolean.TRUE;
	}

	/**
	 * @param login
	 * @param senha
	 * @return
	 */
	private Authentication criarAutenticacao(String login, String senha) {
		if (login == null || senha == null) {
			throw new IllegalArgumentException(
					"Tentativa de criação de autenticação com login ou senha nula");
		}

		Authentication authentication = null;

		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					login, senha);

			authentication = this.authenticationManager.authenticate(token);

		} catch (BadCredentialsException bce) {
			return null;
		}

		return authentication;
	}

}
