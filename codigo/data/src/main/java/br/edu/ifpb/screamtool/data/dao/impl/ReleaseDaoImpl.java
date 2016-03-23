/**
 * 
 */
package br.edu.ifpb.screamtool.data.dao.impl;

import java.util.ArrayList;
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
	public List<Release> buscarPorProjeto(Long idProjeto) {
		Query query = this.entityManager
				.createNamedQuery("Release.buscarProProjeto");
		query.setParameter("idProjeto", idProjeto);

		return buildReleaseList(query.getResultList());
	}

	private List<Release> buildReleaseList(List<Object[]> dados) {

		List<Release> resultado = new ArrayList<>();
		List<Sprint> sprints = null;
		List<ItemBacklog> itensBacklog = null;
		Long lastReleaseId = 0l;
		Long lastSprintId = 0l;
		Long lastItemBacklogId = 0l;

		for (Object[] dado : dados) {
			Long releaseId = (Long) dado[0];
			Long sprintId = (Long) dado[2];
			Long itemBacklogId = (Long) dado[4];
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
				sprints.add(sprint);
			}
			
			if(itemBacklogId != null && !itemBacklogId.equals(lastItemBacklogId)) {
				lastItemBacklogId = itemBacklogId;
				ItemBacklog itemBacklog = new ItemBacklog();
				itemBacklog.setId(itemBacklogId);
				itemBacklog.setDescricao((String) dado[5]);
				itensBacklog.add(itemBacklog);
			}
		}

		return resultado;
	}

}
