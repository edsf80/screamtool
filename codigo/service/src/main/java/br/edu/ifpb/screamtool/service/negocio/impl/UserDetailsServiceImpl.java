/**
 * 
 */
package br.edu.ifpb.screamtool.service.negocio.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifpb.screamtool.data.dao.UsuarioDao;
import br.edu.ifpb.screamtool.domain.entity.Papel;
import br.edu.ifpb.screamtool.domain.entity.Permissao;
import br.edu.ifpb.screamtool.domain.entity.Projeto;
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
	@Transactional
	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException {

		Usuario usuario = usuarioDao.bucarPorLogin(login);

		if (usuario == null) {
			throw new UsernameNotFoundException("Usuario nao encontrado");
		}

		UsuarioVO usuarioVO = new UsuarioVO();

		usuarioVO.setUsername(usuario.getLogin());
		usuarioVO.setPassword(usuario.getSenha());
		usuarioVO.setId(usuario.getId());
		usuarioVO.setNome(usuario.getNome());

		Set<Projeto> projetos = usuario.getProjetos();
		usuarioVO.setProjetos(projetos);

		Set<Papel> papeis = usuario.getPapeis();
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (Papel papel : papeis) {
			List<Permissao> permissoes = papel.getPermissoes();

			for (Permissao permissao : permissoes) {
				authorities.add(new SimpleGrantedAuthority(permissao
						.getCodigo()));
			}

		}

		usuarioVO.setAuthorities(authorities);

		return usuarioVO;
	}

	/**
	 * @param usuarioDao
	 */
	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

}
