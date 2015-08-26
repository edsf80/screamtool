package br.edu.ifpb.screamtool.visao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.edu.ifpb.screamtool.domain.entity.Produto;
import br.edu.ifpb.screamtool.domain.entity.Projeto;
import br.edu.ifpb.screamtool.service.negocio.ProdutoService;
import br.edu.ifpb.screamtool.service.negocio.ProjetoService;

@Controller
@RequestMapping("/projeto")
public class ProjetoController {
	
	/**
	 * 
	 */
	@Autowired
	@Qualifier("projetoService")
	private ProjetoService projetoService;
	
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
		
		List<Projeto> projetos = projetoService.buscarTodos();
		List<Produto> produtos = produtoService.buscarTodos();
		
		model.addAttribute("produtos", produtos);
		model.addAttribute("projetos", projetos);
		 
		return "projeto";
	}

}
