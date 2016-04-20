/**
 * 
 */
package br.edu.ifpb.screamtool.visao.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.screamtool.domain.entity.Sprint;
import br.edu.ifpb.screamtool.service.negocio.SprintService;

/**
 * @author edsf
 *
 */
@RestController
@RequestMapping(value = "/sprint")
public class SprintRestService {

	@Autowired
	@Qualifier("sprintService")
	private SprintService sprintService;

	/**
	 * @param sprint
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Sprint salvarSprint(@RequestBody Sprint sprint) {

		Sprint resultado = null;

		if (sprint.getId() == null) {
			resultado = sprintService.criarVerificacaoDatas(sprint);
		} else {
			resultado = sprintService.atualizarVerificacaoDatas(sprint);
		}
		
		//Linha para evitar que o jackson tente serializar um atributo lazy.
		resultado.setItensBacklog(null);
		resultado.getRelease().setSprints(null);

		return resultado;
	}

	/**
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Sprint buscarSprintPorId(@PathVariable("id") Long id) {

		Sprint resultado = sprintService.buscarPorId(id);
		//Linha para evitar que o jackson tente serializar um atributo lazy.
		resultado.setItensBacklog(null);
		resultado.getRelease().setSprints(null);

		return resultado;

	}
}
