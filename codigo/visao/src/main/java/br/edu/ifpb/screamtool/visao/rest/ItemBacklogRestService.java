/**
 * 
 */
package br.edu.ifpb.screamtool.visao.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping(value = "/itembacklog")
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
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ItemBacklog salvarItemBacklog(
			@RequestBody ItemBacklog itemBacklog,
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

	@RequestMapping(method = RequestMethod.GET)
	public List<ItemBacklog> buscarTodosPorProduto(
			@ModelAttribute("projetoAberto") Projeto projetoAberto) {

		List<ItemBacklog> resultado;

		if (projetoAberto.getId() == null) {

			resultado = new ArrayList<>();
		} else {

			resultado = itemBacklogService.buscarTodosPorProduto(projetoAberto
					.getProduto().getId());
			for (ItemBacklog itemBacklog : resultado) {
				itemBacklog.setTarefas(null);
				itemBacklog.setProduto(null);
			}
		}

		return resultado;
	}

	/**
	 * @param itemBacklog
	 * @return
	 */
	@PreAuthorize("hasRole('perm_excluir_item_backlog')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void excluirItemBacklog(@PathVariable Long id) {

		ItemBacklog itemBacklog = new ItemBacklog();
		itemBacklog.setId(id);

		itemBacklogService.apagar(itemBacklog);
	}

	/**
	 * @param itemBacklog
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public void atualizarItemBacklog(
			@RequestBody ItemBacklog itemBacklog) {

		itemBacklogService.mergeItemBacklog(itemBacklog);
	}
}
