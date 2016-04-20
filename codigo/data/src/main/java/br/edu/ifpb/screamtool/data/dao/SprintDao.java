/**
 * 
 */
package br.edu.ifpb.screamtool.data.dao;

import java.util.Date;
import java.util.List;

import br.edu.ifpb.screamtool.domain.entity.Sprint;

/**
 * @author edsf
 *
 */
public interface SprintDao extends GenericDao<Sprint, Long> {

	/**
	 * Busca Sprints que estejam dentro do range do inicio e termino passados
	 * para um determinado projeto.
	 * 
	 * @param idProjeto
	 * @param inicio
	 * @param termino
	 * @return
	 */
	List<Sprint> buscarPorDatasNoProjeto(Long idProjeto, Date inicio,
			Date termino);

	/**
	 * @param idProjeto
	 * @param idSprint
	 * @param inicio
	 * @param termino
	 * @return
	 */
	List<Sprint> buscarPorDatasNoProjetoDiferenteDoAtual(Long idProjeto,
			Long idSprint, Date inicio, Date termino);
}
