/**
 * 
 */
package br.edu.ifpb.screamtool.data.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.edu.ifpb.screamtool.data.dao.SprintDao;
import br.edu.ifpb.screamtool.domain.entity.Sprint;

/**
 * @author edsf
 *
 */
@Repository("sprintDao")
public class SprintDaoImpl extends GenericDaoImpl<Sprint, Long> implements
		SprintDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.edu.ifpb.screamtool.data.dao.SprintDao#buscarPorDatasNoProjeto(java
	 * .lang.Long, java.util.Date, java.util.Date)
	 */
	@Override
	public List<Sprint> buscarPorDatasNoProjeto(Long idProjeto, Date inicio,
			Date termino) {
		TypedQuery<Sprint> query = this.entityManager.createNamedQuery(
				"Sprint.findByDateRangePerProject", Sprint.class);
		query.setParameter("idProjeto", idProjeto);
		query.setParameter("dataInicio", inicio);
		query.setParameter("dataTermino", termino);

		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.edu.ifpb.screamtool.data.dao.SprintDao#
	 * buscarPorDatasNoProjetoDiferenteDoAtual(java.lang.Long, java.lang.Long,
	 * java.util.Date, java.util.Date)
	 */
	@Override
	public List<Sprint> buscarPorDatasNoProjetoDiferenteDoAtual(Long idProjeto,
			Long idSprint, Date inicio, Date termino) {
		TypedQuery<Sprint> query = this.entityManager
				.createNamedQuery(
						"Sprint.findByDateRangePerProjectDifferentSprint",
						Sprint.class);
		query.setParameter("idProjeto", idProjeto);
		query.setParameter("idSprint", idSprint);
		query.setParameter("dataInicio", inicio);
		query.setParameter("dataTermino", termino);

		return query.getResultList();
	}
}
