/**
 * 
 */
package br.edu.ifpb.screamtool.data.dao;

import java.util.List;

import br.edu.ifpb.screamtool.domain.entity.ItemBacklog;

/**
 * @author edsf
 *
 */
public interface ItemBacklogDao extends GenericDao<ItemBacklog, Long> {

	/**
	 * Busca todos os itens de backlog por produto, através do id do produto.
	 * 
	 * @param idProduto
	 *            Id do produto que se deseja os itens de backlog.
	 * @return A lista de itens de backlog daquele produto. Caso o produto não
	 *         exista ou não possua itens de backlog o retorno será uma lista
	 *         vazia.
	 */
	List<ItemBacklog> buscarTodosPorProduto(Long idProduto);

}
