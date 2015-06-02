/**
 * 
 */
package br.edu.ifpb.screamtool.service.negocio;

import java.util.List;

/**
 * @author edsf
 *
 */
public interface GenericService<T,K> {
	
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
	
	/**
	 * @param entidade
	 */
	void apagar(T entidade);
}
