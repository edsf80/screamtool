/**
 * 
 */
package br.edu.ifpb.screamtool.service.negocio;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author edsf
 *
 */
public interface GenericService<T, K> {

	/**
	 * @param entidade
	 * @return
	 */
	T criar(@Valid @NotNull T entidade);

	/**
	 * @param id
	 * @return
	 */
	T buscarPorId(@NotNull K id);

	/**
	 * @param entidade
	 * @return
	 */
	T atualizar(@NotNull @Valid T entidade);

	/**
	 * @return
	 */
	List<T> buscarTodos();

	/**
	 * @param entidade
	 */
	void apagar(@NotNull @Valid T entidade);
}
