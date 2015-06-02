/**
 * 
 */
package br.edu.ifpb.screamtool.service.negocio.impl;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
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
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, Long>
		implements UsuarioService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public UsuarioServiceImpl(UsuarioDao dao) {
		this.dao = dao;
	}

	@Transactional
	public boolean registrarUsuario(@NotNull @Valid RegistroUsuarioVO usuario) {

		Usuario user = new Usuario();
		user.setId(usuario.getId());
		user.setLogin(usuario.getLogin());
		user.setSenha(passwordEncoder.encode(usuario.getSenha()));
		user.setNome(usuario.getNome());

		this.criar(user);

		return true;

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
