/**
 * 
 */
package br.edu.ifpb.screamtool.service.negocio;


/**
 * @author edsf
 *
 */
public interface LoginService {

	boolean autenticarUsuario(String login, String senha);
}
