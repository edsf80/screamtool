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
import org.springframework.web.bind.annotation.SessionAttributes;

import br.edu.ifpb.screamtool.domain.entity.ItemBacklog;
import br.edu.ifpb.screamtool.domain.entity.Produto;
import br.edu.ifpb.screamtool.domain.entity.Projeto;
import br.edu.ifpb.screamtool.service.negocio.ItemBacklogService;

/**
 * @author edsf
 *
 */
@RestController
@RequestMapping(value = "/service/itembacklog")
@SessionAttributes("projetoAberto")
public class ItemBacklogRestService {

	/**
	 * 
	 */
	@Autowired
	@Qualifier("itemBacklogService")
	private ItemBacklogService itemBacklogService;

	/**
	 * @param itemBacklog
	 * @param projeto
	 * @return
	 */
	@PreAuthorize("hasRole('perm_salvar_item_backlog')")
	@RequestMapping(value = "/salvarItemBacklog.rest", method = RequestMethod.POST)
	public @ResponseBody ItemBacklog salvarItemBacklog(
			@ModelAttribute ItemBacklog itemBacklog,
			@ModelAttribute("projetoAberto") Projeto projeto) {

		ItemBacklog resultado = null;

		Produto produto = projeto.getProduto();
		itemBacklog.setProduto(produto);

		if (itemBacklog.getId() == null) {

			resultado = itemBacklogService.criar(itemBacklog);
		} else {
			resultado = itemBacklogService.atualizar(itemBacklog);
			// Limpando o proxy da referencia a projeto do objeto detached. Sem
			// isso um problema ocorrerá ao editar.
			resultado.setProduto(null);
		}

		return resultado;
	}

	/**
	 * @param itemBacklog
	 * @return
	 */
	@PreAuthorize("hasRole('perm_excluir_item_backlog')")
	@RequestMapping(value = "/excluirItemBacklog.rest", method = RequestMethod.POST)
	public @ResponseBody boolean excluirItemBacklog(
			@ModelAttribute ItemBacklog itemBacklog) {

		itemBacklogService.apagar(itemBacklog);

		return Boolean.TRUE;
	}
}