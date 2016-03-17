/**
 * 
 */
package br.edu.ifpb.screamtool.data.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.edu.ifpb.screamtool.data.dao.ReleaseDao;
import br.edu.ifpb.screamtool.domain.entity.Release;

/**
 * @author edsf
 *
 */
@Repository("releaseDao")
public class ReleaseDaoImpl extends GenericDaoImpl<Release, Long> implements
		ReleaseDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.edu.ifpb.screamtool.data.dao.ReleaseDao#buscarPorProjeto(java.lang
	 * .Long)
	 */
	public List<Release> buscarPorProjeto(Long idProjeto) {
		TypedQuery<Release> query = this.entityManager.createNamedQuery(
				"Release.buscarProProjeto", Release.class);
		query.setParameter("idProjeto", idProjeto);

		return query.getResultList();
	}

}
