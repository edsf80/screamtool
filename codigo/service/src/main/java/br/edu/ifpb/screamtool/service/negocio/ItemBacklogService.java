/**
 * 
 */
package br.edu.ifpb.screamtool.service.negocio;

import java.util.List;

import javax.validation.constraints.NotNull;

import br.edu.ifpb.screamtool.domain.entity.ItemBacklog;

/**
 * @author edsf
 *
 */
public interface ItemBacklogService extends GenericService<ItemBacklog, Long> {

	/**
	 * Busca todos os itens de backlog por produto, através do id do produto.
	 * 
	 * @param idProduto
	 *            Id do produto que se deseja os itens de backlog.
	 * @return A lista de itens de backlog daquele produto. Caso o produto não
	 *         exista ou não possua itens de backlog o retorno será uma lista
	 *         vazia.
	 */
	List<ItemBacklog> buscarTodosPorProduto(@NotNull Long idProduto);

	/**
	 * Busca todos os itens de backlog por produto, através do id do produto. Os
	 * itens retornados dessa busca não devem estar alocados em nenhuma sprint
	 * de nenhum projeto.
	 * 
	 * @param idProduto
	 *            Id do produto que se deseja os itens de backlog.
	 * @return A lista de itens de backlog daquele produto que não estão
	 *         alocadas em sprint alguma. Caso o produto não exista ou não
	 *         possua itens de backlog o retorno será uma lista vazia.
	 */
	List<ItemBacklog> buscarTodosPorProdutoNaoAlocados(@NotNull Long idProduto);

}
