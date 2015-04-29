/**
 * 
 */
package br.edu.ifpb.screamtool.service.business.impl;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import br.edu.ifpb.screamtool.data.dao.UsuarioDao;
import br.edu.ifpb.screamtool.domain.entity.Usuario;
import br.edu.ifpb.screamtool.domain.vo.RegistroUsuarioVO;
import br.edu.ifpb.screamtool.service.business.UsuarioService;

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

		if(usuarioDao.verificarLoginExiste(usuario.getLogin())) {
			throw new RuntimeException("Deu merda na validação"); 
		}
		
		Usuario user = new Usuario();
		user.setId(usuario.getId());
		user.setLogin(usuario.getLogin());
		user.setSenha(passwordEncoder.encode(usuario.getSenha()));
		user.setNome(usuario.getNome());

		usuarioDao.criar(user);

		return true;

	}

}
