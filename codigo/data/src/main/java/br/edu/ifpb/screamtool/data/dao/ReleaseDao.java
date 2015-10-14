/**
 * 
 */
package br.edu.ifpb.screamtool.data.dao;

import java.util.List;

import br.edu.ifpb.screamtool.domain.entity.Release;

/**
 * @author edsf
 *
 */
public interface ReleaseDao extends GenericDao<Release, Long> {

	/**
	 * @param idProjeto
	 * @return
	 */
	List<Release> buscarPorProjeto(Long idProjeto);
}
