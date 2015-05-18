/**
 * 
 */
package br.edu.ifpb.screamtool.service.negocio.impl;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.edu.ifpb.screamtool.service.negocio.LoginService;

/**
 * @author edsf
 *
 */
@Validated
@Service("loginService")
public class LoginServiceImpl implements LoginService {

	/**
	 * 
	 */
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;

	/**
	 * 
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.edu.ifpb.screamtool.service.business.LoginService#autenticarUsuario
	 * (java.lang.String, java.lang.String)
	 */
	public boolean autenticarUsuario(@NotNull String login,
			@NotNull String senha) {

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
