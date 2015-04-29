/**
 * 
 */
package br.edu.ifpb.screamtool.data.dao;

import br.edu.ifpb.screamtool.domain.entity.Usuario;

/**
 * @author edsf
 *
 */
public interface UsuarioDao extends GenericDao<Usuario, Long> {

	/**
	 * Busca o usuário por login. Utilizado para verificar se o mesmo 
	 * 
	 * @param login
	 * @return
	 */
	boolean verificarLoginExiste(String login);
}
