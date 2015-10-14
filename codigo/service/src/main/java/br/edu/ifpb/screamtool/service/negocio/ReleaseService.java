/**
 * 
 */
package br.edu.ifpb.screamtool.service.negocio;

import java.util.List;

import javax.validation.constraints.NotNull;

import br.edu.ifpb.screamtool.domain.entity.Release;

/**
 * @author edsf
 *
 */
public interface ReleaseService extends GenericService<Release, Long> {

	/**
	 * @param idProjeto
	 * @return
	 */
	List<Release> buscarPorProjeto(@NotNull Long idProjeto);
}
