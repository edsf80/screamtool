/**
 * 
 */
package br.edu.ifpb.screamtool.service.negocio.impl;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import br.edu.ifpb.screamtool.data.dao.UsuarioDao;
import br.edu.ifpb.screamtool.domain.entity.Usuario;
import br.edu.ifpb.screamtool.service.negocio.UsuarioService;
import br.edu.ifpb.screamtool.service.vo.RegistroUsuarioVO;
import br.edu.ifpb.screamtool.service.vo.UsuarioVO;

/**
 * @author edsf
 *
 */
@Validated
@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	@Qualifier("usuarioDao")
	private UsuarioDao usuarioDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public boolean registrarUsuario(@NotNull @Valid RegistroUsuarioVO usuario) {

		Usuario user = new Usuario();
		user.setId(usuario.getId());
		user.setLogin(usuario.getLogin());
		user.setSenha(passwordEncoder.encode(usuario.getSenha()));
		user.setNome(usuario.getNome());

		usuarioDao.criar(user);

		return true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.edu.ifpb.screamtool.service.negocio.UsuarioService#buscarPorId(java
	 * .lang.Long)
	 */
	public Usuario buscarPorId(@NotNull Long id) {

		return usuarioDao.buscarPorId(id);
	}

	/**
	 * @param usuarioDao
	 */
	protected void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.edu.ifpb.screamtool.service.negocio.UsuarioService#buscarUsuarioLogado
	 * ()
	 */
	public UsuarioVO buscarUsuarioLogado() {
		
		UsuarioVO usuarioAutenticado = (UsuarioVO) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		
		return usuarioAutenticado;
	}

}
