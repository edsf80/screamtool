/**
 * 
 */
package br.edu.ifpb.screamtool.visao.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.screamtool.domain.vo.RegistroUsuarioVO;
import br.edu.ifpb.screamtool.service.business.UsuarioService;

/**
 * @author edsf
 *
 */
@RestController
@RequestMapping(value = "/service")
public class RegistroRestService {

	@Autowired
	@Qualifier("usuarioService")
	private UsuarioService usuarioService;

	@RequestMapping(value = "/registro.rest", method = RequestMethod.POST)
	@ResponseBody
	public boolean registrarUsuario(@ModelAttribute RegistroUsuarioVO usuario) {

		return usuarioService.registrarUsuario(usuario);

	}
}
