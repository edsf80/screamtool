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
import br.edu.ifpb.screamtool.service.negocio.ItemBacklogService;

/**
 * @author edsf
 *
 */
@Controller
@RequestMapping("/private/itembacklog")
@SessionAttributes("projetoAberto")
public class ItemBacklogController {

	@Autowired
	@Qualifier("itemBacklogService")
	private ItemBacklogService itemBacklogService;

	@RequestMapping(method = RequestMethod.GET)
	public String loadPage(ModelMap model,
			@ModelAttribute("projetoAberto") Projeto projetoAberto) {

		List<ItemBacklog> itensBacklog = itemBacklogService
				.buscarTodosPorProduto(projetoAberto.getProduto().getId());

		model.addAttribute("itensBacklog", itensBacklog);

		return "itembacklog";
	}
}
