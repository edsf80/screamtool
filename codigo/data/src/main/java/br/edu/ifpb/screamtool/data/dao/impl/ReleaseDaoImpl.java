/**
 * 
 */
package br.edu.ifpb.screamtool.data.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.edu.ifpb.screamtool.data.dao.ReleaseDao;
import br.edu.ifpb.screamtool.domain.entity.ItemBacklog;
import br.edu.ifpb.screamtool.domain.entity.Release;
import br.edu.ifpb.screamtool.domain.entity.Sprint;

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
	@SuppressWarnings("unchecked")
	public List<Release> buscarPorProjeto(Long idProjeto) {
		Query query = this.entityManager
				.createNamedQuery("Release.buscarProProjeto");
		query.setParameter("idProjeto", idProjeto);

		return buildReleaseList(query.getResultList());
	}

	/**
	 * Constroi um objeto release a partir de dados de uma lista de arrays. Cada
	 * array da lista deve ter o formato onde o primeiro elemento é o código do
	 * release, o segundo a descrição do release, o terceiro o código do sprint,
	 * o quarto a descrição da sprint, o quinto o código dos itens de backlog e
	 * o sexto a descrição do item de backlog. Esse método foi criado, pois a
	 * busca pelo jpql padrão do objeto iria trazer dados desnecessários, além
	 * de campos com lazy loading o que faria necessária a busca de itens filhos
	 * em outros selects.
	 * 
	 * @param dados
	 * @return Uma lista de releases com nome e descrição com seus respectivos
	 *         sprints e itens de backlog.
	 */
	private List<Release> buildReleaseList(List<Object[]> dados) {

		List<Release> resultado = new ArrayList<>();
		
		if(dados == null) {
			return resultado;
		}
		
		List<Sprint> sprints = null;
		List<ItemBacklog> itensBacklog = null;
		Long lastReleaseId = 0l;
		Long lastSprintId = 0l;
		Long lastItemBacklogId = 0l;

		for (Object[] dado : dados) {
			Long releaseId = (Long) dado[0];
			Long sprintId = (Long) dado[2];
			Long itemBacklogId = (Long) dado[6];
			Release release = null;

			if (!releaseId.equals(lastReleaseId)) {
				release = new Release();
				sprints = new ArrayList<>();
				lastReleaseId = releaseId;
				release.setId(releaseId);
				release.setNome((String) dado[1]);
				release.setSprints(sprints);
				resultado.add(release);
			}

			if (sprintId != null && !sprintId.equals(lastSprintId)) {
				lastSprintId = sprintId;
				Sprint sprint = new Sprint();
				itensBacklog = new ArrayList<ItemBacklog>();
				sprint.setItensBacklog(itensBacklog);
				sprint.setId((Long) dado[2]);
				sprint.setNome((String) dado[3]);
				sprint.setDataInicio((Date) dado[4]); 
				sprint.setDataTermino((Date) dado[5]);
				sprints.add(sprint);
			}

			if (itemBacklogId != null
					&& !itemBacklogId.equals(lastItemBacklogId)) {
				lastItemBacklogId = itemBacklogId;
				ItemBacklog itemBacklog = new ItemBacklog();
				itemBacklog.setId(itemBacklogId);
				itemBacklog.setDescricao((String) dado[7]);
				itensBacklog.add(itemBacklog);
			}
		}

		return resultado;
	}

}
