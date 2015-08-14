/**
 * 
 */
package br.edu.ifpb.screamtool.service.negocio;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.edu.ifpb.screamtool.data.dao.UsuarioDao;
import br.edu.ifpb.screamtool.domain.entity.Usuario;
import br.edu.ifpb.screamtool.service.vo.RegistroUsuarioVO;
import br.edu.ifpb.screamtool.service.vo.UsuarioVO;

/**
 * @author edsf
 *
 */
public interface UsuarioService extends GenericService<Usuario, Long> {

	/**
	 * @param usuario
	 * @return
	 */
	boolean registrarUsuario(@NotNull @Valid RegistroUsuarioVO usuario);

	/**
	 * @return
	 */
	UsuarioVO buscarUsuarioLogado();
	
	/**
	 * @param dao
	 */
	void setDao(UsuarioDao dao);
	
	/**
	 * @param idProjeto
	 * @return
	 */
	List<Usuario> buscarTodosPorProjeto(@NotNull Long idProjeto);

}
