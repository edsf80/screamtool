/**
 * 
 */
package br.edu.ifpb.screamtool.data.dao;

import java.util.List;

/**
 * @author edsf
 *
 */
public interface GenericDao<T, K> {
	/**
	 * @param entidade
	 * @return
	 */
	T criar(T entidade);
	
	/**
	 * @param id
	 * @return
	 */
	T buscarPorId(K id);
	
	/**
	 * @param entidade
	 * @return
	 */
	T atualizar(T entidade);
	
	/**
	 * @return
	 */
	List<T> buscarTodos();
}
