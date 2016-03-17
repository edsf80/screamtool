/**
 * 
 */
package br.edu.ifpb.screamtool.data.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.edu.ifpb.screamtool.data.dao.UsuarioDao;
import br.edu.ifpb.screamtool.domain.entity.Usuario;

/**
 * @author edsf
 *
 */
@Repository("usuarioDao")
public class UsuarioDaoImpl extends GenericDaoImpl<Usuario, Long> implements
		UsuarioDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.edu.ifpb.screamtool.data.dao.UsuarioDao#verificarLoginExiste(java.
	 * lang.String)
	 */
	public boolean verificarLoginExiste(String login) {

		Query query = this.entityManager
				.createNamedQuery("Usuario.verificarExiste");
		query.setParameter("login", login);

		return query.getResultList().size() > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.edu.ifpb.screamtool.data.dao.UsuarioDao#bucarPorLogin(java.lang.String
	 * )
	 */
	public Usuario bucarPorLogin(String login) {

		Query query = this.entityManager
				.createNamedQuery("Usuario.buscarPorLogin");
		query.setParameter("login", login);

		@SuppressWarnings("unchecked")
		List<Usuario> usuarios = query.getResultList();

		Usuario resultado = usuarios.isEmpty() ? null : usuarios.get(0);

		return resultado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.edu.ifpb.screamtool.data.dao.UsuarioDao#buscarTodosPorProjeto(java
	 * .lang.Long)
	 */
	public List<Usuario> buscarTodosPorProjeto(Long idProjeto) {

		Query query = this.entityManager
				.createNamedQuery("Usuario.buscarTodosPorProjeto");
		query.setParameter("projeto", idProjeto);

		@SuppressWarnings("unchecked")
		List<Usuario> resultado = query.getResultList();

		return resultado;
	}

}
