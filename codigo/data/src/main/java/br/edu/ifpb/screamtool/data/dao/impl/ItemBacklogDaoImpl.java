/**
 * 
 */
package br.edu.ifpb.screamtool.data.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.edu.ifpb.screamtool.data.dao.ItemBacklogDao;
import br.edu.ifpb.screamtool.domain.entity.ItemBacklog;

/**
 * @author edsf
 *
 */
@Repository("itemBacklogDao")
public class ItemBacklogDaoImpl extends GenericDaoImpl<ItemBacklog, Long>
		implements ItemBacklogDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.edu.ifpb.screamtool.data.dao.ItemBacklogDao#buscarTodosPorProduto(
	 * java.lang.Long)
	 */
	public List<ItemBacklog> buscarTodosPorProduto(Long idProduto) {

		if (idProduto == null) {
			throw new IllegalArgumentException(
					"Itembacklogdao.buscarTodosPorProduto deve receber id do produto não nulo.");
		}

		Query query = this.entityManager
				.createNamedQuery("ItemBacklog.buscarPorProduto");
		query.setParameter("produto", idProduto);

		@SuppressWarnings("unchecked")
		List<ItemBacklog> resultado = query.getResultList();

		return resultado;
	}

}
