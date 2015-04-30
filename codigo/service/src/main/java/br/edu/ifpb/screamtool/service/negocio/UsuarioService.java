/**
 * 
 */
package br.edu.ifpb.screamtool.service.negocio;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.edu.ifpb.screamtool.service.vo.RegistroUsuarioVO;

/**
 * @author edsf
 *
 */
public interface UsuarioService {
	
	boolean registrarUsuario(@NotNull @Valid RegistroUsuarioVO usuario);

}
