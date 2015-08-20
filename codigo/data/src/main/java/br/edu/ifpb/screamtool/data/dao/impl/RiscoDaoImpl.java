/**
 * 
 */
package br.edu.ifpb.screamtool.data.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.edu.ifpb.screamtool.data.dao.RiscoDao;
import br.edu.ifpb.screamtool.domain.entity.Risco;
import br.edu.ifpb.screamtool.domain.entity.Usuario;

/**
 * @author edsf
 *
 */
@Repository("riscoDao")
public class RiscoDaoImpl extends GenericDaoImpl<Risco, Long> implements
		RiscoDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.edu.ifpb.screamtool.data.dao.RiscoDao#buscarTodosPorProjeto(java.lang
	 * .Long)
	 */
	@Override
	public List<Risco> buscarTodosPorProjeto(Long idProjeto) {

		if (idProjeto == null) {
			throw new IllegalArgumentException(
					"Riscodao.buscarTodosPorProjeto deve receber id do projeto não nulo.");
		}

		Query query = this.entityManager
				.createNamedQuery("Risco.buscarPorProjeto");
		query.setParameter("projeto", idProjeto);

		@SuppressWarnings("unchecked")
		List<Risco> resultado = query.getResultList();

		return resultado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.edu.ifpb.screamtool.data.dao.RiscoDao#buscarPorIdSemAssociacoes(java
	 * .lang.Long)
	 */
	@Override
	public Risco buscarPorIdSemAssociacoes(Long id) {

		if (id == null) {
			throw new IllegalArgumentException(
					"Riscodao.buscarPorIdSemAssociacoes deve receber id não nulo.");
		}

		Query query = this.entityManager
				.createNamedQuery("Risco.buscarPorIdSemAssociacoes");
		query.setParameter("id", id);

		Risco resultado = query.getResultList().size() == 0 ? null : this
				.buildRisco((Object[]) query.getResultList().get(0));

		return resultado;
	}

	/**
	 * @param dados
	 * @return
	 */
	private Risco buildRisco(Object[] dados) {

		Risco risco = new Risco();
		risco.setId((Long) dados[0]);
		risco.setDescricao((String) dados[1]);
		risco.setContingencia((String) dados[2]);
		risco.setProbabilidade((Risco.NivelProbImpacto) dados[3]);
		risco.setImpacto((Risco.NivelProbImpacto) dados[4]);
		risco.setStatus((Risco.RiscoStatus) dados[5]);
		risco.setMitigacao((String) dados[6]);

		Usuario responsavel = null;
		
		if (dados[7] != null) {
			responsavel = new Usuario();
			responsavel.setId((Long) dados[7]);
			responsavel.setNome((String) dados[8]);
		}
		
		risco.setResponsavel(responsavel);

		return risco;

	}
}
