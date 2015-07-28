package br.edu.ifpb.screamtool.visao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.edu.ifpb.screamtool.domain.entity.Produto;
import br.edu.ifpb.screamtool.service.negocio.ProdutoService;

@Controller
@RequestMapping("/private/produto")
public class ProdutoController {

	/**
	 * 
	 */
	@Autowired
	@Qualifier("produtoService")
	private ProdutoService produtoService;

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String loadPage(ModelMap model) {
		List<Produto> produtos = produtoService.buscarTodos();

		model.addAttribute("produtos", produtos);

		return "produto";
	}

}
