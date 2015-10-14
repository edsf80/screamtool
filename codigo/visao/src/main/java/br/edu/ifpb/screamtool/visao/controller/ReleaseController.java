/**
 * 
 */
package br.edu.ifpb.screamtool.visao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import br.edu.ifpb.screamtool.domain.entity.Projeto;
import br.edu.ifpb.screamtool.domain.entity.Release;
import br.edu.ifpb.screamtool.service.negocio.ReleaseService;

/**
 * @author edsf
 *
 */
@Controller
@RequestMapping("/release")
@SessionAttributes("projetoAberto")
public class ReleaseController {

	/**
	 * 
	 */
	@Autowired
	@Qualifier("releaseService")
	private ReleaseService releaseService;

	/**
	 * @return
	 */
	@ModelAttribute("projetoAberto")
	public Projeto createProjeto() {
		return new Projeto();
	}

	/**
	 * @param model
	 * @param projetoAberto
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String loadPage(ModelMap model,
			@ModelAttribute("projetoAberto") Projeto projetoAberto) {

		String resultado;

		if (projetoAberto.getId() == null) {
			model.remove("projetoAberto");
			resultado = "main";
		} else {
			List<Release> releases = releaseService
					.buscarPorProjeto(projetoAberto.getId());
			
			model.addAttribute("releases", releases);
			
			resultado = "release";
		}

		return resultado;
	}

}
