/**
 * 
 */
package br.edu.ifpb.screamtool.service.negocio;

import br.edu.ifpb.screamtool.domain.entity.Sprint;
import br.edu.ifpb.screamtool.service.validator.ValidSprint;

/**
 * @author edsf
 *
 */
public interface SprintService extends GenericService<Sprint, Long> {

	Sprint criarVerificacaoDatas(@ValidSprint(message = "Período do sprint sobrepõe outro sprint") Sprint sprint);

	Sprint atualizarVerificacaoDatas(@ValidSprint(message = "Período do sprint sobrepõe outro sprint") Sprint sprint);
}
