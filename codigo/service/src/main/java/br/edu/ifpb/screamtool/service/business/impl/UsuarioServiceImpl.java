/**
 * 
 */
package br.edu.ifpb.screamtool.service.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import br.edu.ifpb.screamtool.data.dao.UsuarioDao;
import br.edu.ifpb.screamtool.data.domain.Usuario;
import br.edu.ifpb.screamtool.service.business.UsuarioService;

/**
 * @author edsf
 *
 */
@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	@Qualifier("usuarioDao")
	private UsuarioDao usuarioDao;
	
	@Transactional
	public boolean registrarUsuario(Usuario usuario) {
		
		usuarioDao.criar(usuario);
		
		return true;
		
	}

}
