/**
 * 
 */
package br.edu.ifpb.screamtool.visao.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.screamtool.domain.entity.Produto;
import br.edu.ifpb.screamtool.service.negocio.ProdutoService;

/**
 * @author edsf
 *
 */
@RestController
@RequestMapping(value = "/service")
public class ProdutoRestService {

	@Autowired
	private ProdutoService produtoService;

	/**
	 * @return
	 */
	@RequestMapping(value = "/buscarTodosProdutos.rest", method = RequestMethod.GET)
	public @ResponseBody List<Produto> buscarTodos() {

		return produtoService.buscarTodos();
	}

	/**
	 * @param produto
	 * @return
	 */
	@RequestMapping(value = "/salvarProduto.rest", method = RequestMethod.POST)
	public @ResponseBody Produto salvarProduto(@ModelAttribute Produto produto) {

		Produto resultado = null;

		if (produto.getId() == null) {

			resultado = produtoService.criar(produto);
		} else {
			resultado = produtoService.atualizar(produto);
		}

		return resultado;
	}

}
