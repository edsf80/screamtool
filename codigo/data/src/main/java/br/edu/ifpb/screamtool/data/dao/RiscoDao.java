/**
 * 
 */
package br.edu.ifpb.screamtool.data.dao;

import java.util.List;

import br.edu.ifpb.screamtool.domain.entity.Risco;

/**
 * @author edsf
 *
 */
public interface RiscoDao extends GenericDao<Risco, Long> {

	List<Risco> buscarTodosPorProjeto(Long idProjeto);
	
	Risco buscarPorIdSemAssociacoes(Long id);
}
