/**
 * 
 */
package br.edu.ifpb.screamtool.service.negocio;

import java.util.List;

import javax.validation.constraints.NotNull;

import br.edu.ifpb.screamtool.domain.entity.Risco;

/**
 * @author edsf
 *
 */
public interface RiscoService extends GenericService<Risco, Long> {

	/**
	 * @param idProjeto
	 * @return
	 */
	List<Risco> buscarTodosPorProjeto(@NotNull Long idProjeto);
	
	/**
	 * @param id
	 * @return
	 */
	Risco buscarPorIdSemAssociacoes(@NotNull Long id);
}
