/**
 * 
 */
package br.edu.ifpb.screamtool.service.negocio.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifpb.screamtool.data.dao.UsuarioDao;
import br.edu.ifpb.screamtool.domain.entity.Projeto;
import br.edu.ifpb.screamtool.domain.entity.Usuario;
import br.edu.ifpb.screamtool.service.vo.ProjetoVO;
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

		List<Projeto> projetos = usuario.getProjetos();

		if (projetos != null && !projetos.isEmpty()) {
			List<ProjetoVO> projetosVO = new ArrayList<>();

			for (Projeto projeto : projetos) {
				ProjetoVO novoProjeto = new ProjetoVO();
				novoProjeto.setId(projeto.getId());
				novoProjeto.setNome(projeto.getNome());
				projetosVO.add(novoProjeto);
			}

			usuarioVO.setProjetos(projetosVO);
		}

		return usuarioVO;
	}

	/**
	 * @param usuarioDao
	 */
	protected void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

}
