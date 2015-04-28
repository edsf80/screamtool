/**
 * 
 */
package br.edu.ifpb.screamtool.data.dao.impl;

import org.springframework.stereotype.Repository;

import br.edu.ifpb.screamtool.data.dao.UsuarioDao;
import br.edu.ifpb.screamtool.data.domain.Usuario;

/**
 * @author edsf
 *
 */
@Repository("usuarioDao")
public class UsuarioDaoImpl extends GenericDaoImpl<Usuario, Long> implements
		UsuarioDao {

	

}
