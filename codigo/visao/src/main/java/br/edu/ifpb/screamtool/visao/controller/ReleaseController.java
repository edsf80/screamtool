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

import br.edu.ifpb.screamtool.domain.entity.ItemBacklog;
import br.edu.ifpb.screamtool.domain.entity.Projeto;
import br.edu.ifpb.screamtool.domain.entity.Release;
import br.edu.ifpb.screamtool.service.negocio.ItemBacklogService;
import br.edu.ifpb.screamtool.service.negocio.ReleaseService;
import br.edu.ifpb.screamtool.visao.form.ReleaseForm;

/**
 * @author edsf
 *
 */
@Controller
@RequestMapping("/private/release")
@SessionAttributes("projetoAberto")
public class ReleaseController {

	/**
	 * 
	 */
	@Autowired
	@Qualifier("releaseService")
	private ReleaseService releaseService;
	
	@Autowired
	@Qualifier("itemBacklogService")
	private ItemBacklogService itemBacklogService;

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
			ReleaseForm releaseForm = new ReleaseForm();
			
			List<Release> releases = releaseService
					.buscarPorProjeto(projetoAberto.getId());
			
			releaseForm.setReleases(releases);
			
			List<ItemBacklog> itensBacklogNaoAlocados = itemBacklogService
					.buscarTodosPorProdutoNaoAlocados(projetoAberto.getProduto().getId());
			releaseForm.setItensBacklogNaoAlocados(itensBacklogNaoAlocados);
			
			model.addAttribute("releaseForm", releaseForm);
			
			resultado = "release";
		}

		return resultado;
	}

}
