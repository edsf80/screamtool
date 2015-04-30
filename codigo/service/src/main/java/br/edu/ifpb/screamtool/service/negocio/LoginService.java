/**
 * 
 */
package br.edu.ifpb.screamtool.service.negocio;

import javax.validation.constraints.NotNull;

/**
 * @author edsf
 *
 */
public interface LoginService {

	/**
	 * @param login
	 * @param senha
	 * @return
	 */
	boolean autenticarUsuario(@NotNull String login, @NotNull String senha);
}
