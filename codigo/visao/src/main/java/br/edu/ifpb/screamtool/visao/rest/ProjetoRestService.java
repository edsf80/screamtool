/**
 * 
 */
package br.edu.ifpb.screamtool.visao.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.screamtool.domain.entity.Produto;
import br.edu.ifpb.screamtool.domain.entity.Projeto;
import br.edu.ifpb.screamtool.service.negocio.ProjetoService;
import br.edu.ifpb.screamtool.visao.form.ProjetoForm;

/**
 * @author edsf
 *
 */
@RestController
@RequestMapping(value = "/projeto")
public class ProjetoRestService {

	@Autowired
	@Qualifier("projetoService")
	private ProjetoService projetoService;	

	/**
	 * @param produto
	 * @return
	 */
	@PreAuthorize("hasRole('perm_salvar_projeto')")
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Projeto salvarProjeto(@ModelAttribute ProjetoForm projetoForm) {
		
		Produto produto = new Produto();
		String[] dadosProduto = projetoForm.getIdProduto().split("-");		
		produto.setId(Long.parseLong(dadosProduto[0]));
		produto.setDescricao(dadosProduto[1]);
		
		Projeto resultado = new Projeto();
		resultado.setId(projetoForm.getId());
		resultado.setNome(projetoForm.getNome());
		resultado.setProduto(produto);
		

		if (resultado.getId() == null) {

			resultado = projetoService.criar(resultado);
		} else {
			resultado = projetoService.atualizar(resultado);
		}

		return resultado;
	}
}
