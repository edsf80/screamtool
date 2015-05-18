/**
 * 
 */
package br.edu.ifpb.screamtool.service.negocio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.edu.ifpb.screamtool.data.dao.UsuarioDao;
import br.edu.ifpb.screamtool.domain.entity.Usuario;
import br.edu.ifpb.screamtool.service.vo.UsuarioVO;

/**
 * @author edsf
 *
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	@Qualifier("usuarioDao")
	private UsuarioDao usuarioDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException {

		Usuario usuario = usuarioDao.bucarPorLogin(login);

		if (usuario == null) {
			throw new UsernameNotFoundException("Usuario nao encontrado");
		}

		UsuarioVO usuarioVO = new UsuarioVO();

		usuarioVO.setUsername(usuario.getLogin());
		usuarioVO.setPassword(usuario.getSenha());

		return usuarioVO;
	}

}
