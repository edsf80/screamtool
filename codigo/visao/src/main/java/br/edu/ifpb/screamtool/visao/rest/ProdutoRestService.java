/**
 * 
 */
package br.edu.ifpb.screamtool.visao.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.screamtool.domain.entity.Produto;
import br.edu.ifpb.screamtool.service.negocio.ProdutoService;
import br.edu.ifpb.screamtool.service.negocio.UsuarioService;
import br.edu.ifpb.screamtool.service.vo.UsuarioVO;
import br.edu.ifpb.screamtool.visao.exception.TableResult;
import br.edu.ifpb.screamtool.visao.form.ProdutoForm;

/**
 * @author edsf
 *
 */
@RestController
@RequestMapping(value = "/service/produto")
public class ProdutoRestService {

	@Autowired
	@Qualifier("produtoService")
	private ProdutoService produtoService;
	
	@Autowired
	@Qualifier("usuarioService")
	private UsuarioService usuarioService;

	private TableResult<Produto> buscarTodosProdutos() {
		
		List<Produto> produtos = produtoService.buscarTodos(); 
		
		Produto [] linhas = new Produto[produtos.size()];
		
		TableResult<Produto> resultado = new TableResult<>();
		
		for(int i = 0; i < produtos.size(); i++) {
			linhas[i] = produtos.get(i);
		}
		
		resultado.setData(linhas);

		return resultado;
	}
	
	@PreAuthorize("hasRole('perm_consultar_produto')")
	@RequestMapping(value = "/buscarTodosDados.rest", method = RequestMethod.GET)
	public @ResponseBody ProdutoForm buscarTodosDados() {
		
		ProdutoForm resultado = new ProdutoForm();
		resultado.setProdutos(this.buscarTodosProdutos());
		
		UsuarioVO usuario = usuarioService.buscarUsuarioLogado();		
		resultado.setUsuario(usuario.getNome());
		resultado.setProjetos(usuario.getProjetos());
		resultado.setPermissoes(usuario.getAuthorities());
		
		return resultado;
		
	}

	/**
	 * @param produto
	 * @return
	 */
	@PreAuthorize("hasRole('perm_salvar_produto')")
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
