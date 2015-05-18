/**
 * 
 */
package br.edu.ifpb.screamtool.data.dao.impl;

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
	@Override
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
	@Override
	public Usuario bucarPorLogin(String login) {

		Query query = this.entityManager.createNamedQuery("Usuario.buscarPorLogin");
		query.setParameter("login", login);

		return (Usuario) query.getSingleResult();
	}

}
