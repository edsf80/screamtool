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

import br.edu.ifpb.screamtool.domain.entity.Projeto;
import br.edu.ifpb.screamtool.service.negocio.ProdutoService;
import br.edu.ifpb.screamtool.service.negocio.ProjetoService;
import br.edu.ifpb.screamtool.service.negocio.UsuarioService;
import br.edu.ifpb.screamtool.visao.exception.TableResult;
import br.edu.ifpb.screamtool.visao.form.ProjetoForm;

/**
 * @author edsf
 *
 */
@RestController
@RequestMapping(value = "/service/projeto")
public class ProjetoRestService {

	@Autowired
	@Qualifier("projetoService")
	private ProjetoService projetoService;
	
	@Autowired
	@Qualifier("produtoService")
	private ProdutoService produtoService;
	
	@Autowired
	@Qualifier("usuarioService")
	private UsuarioService usuarioService;

	/**
	 * @return
	 */
	@PreAuthorize("hasRole('perm_consultar_projeto')")
	@RequestMapping(value = "/buscarTodosProjetos.rest", method = RequestMethod.GET)
	public @ResponseBody TableResult<Projeto> buscarTodos() {

		List<Projeto> projetos = projetoService.buscarTodos();

		Projeto[] linhas = new Projeto[projetos.size()];

		TableResult<Projeto> resultado = new TableResult<>();

		for (int i = 0; i < projetos.size(); i++) {
			linhas[i] = projetos.get(i);
		}

		resultado.setData(linhas);

		return resultado;
	}
	
	/**
	 * @return
	 */
	@PreAuthorize("hasRole('perm_consultar_projeto')")
	@RequestMapping(value = "/buscarTodosDados.rest", method = RequestMethod.GET)
	public @ResponseBody ProjetoForm buscarTodosDados() {

		ProjetoForm projetoForm = new ProjetoForm();
		projetoForm.setProjetos(this.buscarTodos());
		projetoForm.setProdutos(produtoService.buscarTodos());
		projetoForm.setUsuario(usuarioService.buscarUsuarioLogado());

		return projetoForm;
	}

	/**
	 * @param produto
	 * @return
	 */
	@PreAuthorize("hasRole('perm_salvar_projeto')")
	@RequestMapping(value = "/salvarProjeto.rest", method = RequestMethod.POST)
	public @ResponseBody Projeto salvarProjeto(@ModelAttribute Projeto projeto) {

		Projeto resultado = null;

		if (projeto.getId() == null) {

			resultado = projetoService.criar(projeto);
		} else {
			resultado = projetoService.atualizar(projeto);
		}

		return resultado;
	}
}
